<template>
  <!-- 标签管理 -->
  <div class="drug-tag">
    <el-row :gutter="20" style="height: 100%">
      <el-col :span="9" style="height: 100%" class="left-col">
        <div style="height: 100%">
          <el-card class="box-card" style="height: 100%; overflow-y: auto;">
            <div slot="header" class="clearfix">
              <span class="f4">标签管理</span>
            </div>
            <el-row style="border-bottom: 1px dashed #ccc; padding: 0 0 15px 15px;">
              <el-input
                size="mini"
                placeholder="请输入内容"
                suffix-icon="el-icon-search"
                v-model="labelName"
              ></el-input>
              <el-button size="mini" type="primary" @click="showLabelModal('add', {})">+新建标签</el-button>
            </el-row>
            <div
              class="label-row"
              @click="handleClick(item)"
              v-for="(item, index) in labelList"
              :key="index"
            >
              <span class="label-name">{{ item.name }}</span>
              <span class="label-del" @click.stop="delLabel(item.id)">删 除</span>
              <span class="label-edit" @click.stop="showLabelModal('edit', item)">编 辑</span>
            </div>
            <Pagination class="text-center" :page="labelPage" @handlePageChange="getLabelList"/>
          </el-card>
        </div>
      </el-col>
      <el-col :span="15" style="height: 100%" class="right-col">
        <el-card class="box-card" style="height: 100%; overflow-y: auto;">
          <div slot="header" class="clearfix">
            <el-button size="mini" type="primary" @click="editMedicine" :disabled="!label.id">编辑药品列表</el-button>
          </div>
          <el-row class="title-row">
            <span>药品通用名</span>
          </el-row>
          <el-row
            style="text-align: center; padding-top: 15px; font-size: 14px;"
            v-if="medicinesMsg != 'success'"
          >
            <span v-if="medicinesMsg">{{ medicinesMsg }}</span>
            <span v-if="!medicinesMsg">请选择标签</span>
          </el-row>
          <div style="height: 100%;">
            <el-row class="label-row" v-for="(item, index) in medicines" :key="index">
              <span class="label-name">{{ item.medicineName }}</span>
            </el-row>
          </div>
          <Pagination class="text-center" :page="page" @handlePageChange="handlePage"/>
        </el-card>
      </el-col>
    </el-row>

    <!-- 添加标签/编辑标签 -->
    <el-dialog
      :title="labelModal.title"
      :visible.sync="labelModal.isShowLabelModal"
      width="400px"
      :before-close="hideLabelModal"
      class="label-modal"
    >
      <span>标签名称：</span>
      <el-input size="small" placeholder="请输入标签名称" v-model="labelModal.data.name"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="hideLabelModal" size="small">取 消</el-button>
        <el-button
          type="primary"
          @click="saveLabelModal('add')"
          size="small"
          v-if="!labelModal.data.id"
        >保 存</el-button>
        <el-button
          type="primary"
          @click="saveLabelModal('edit')"
          size="small"
          v-if="labelModal.data.id"
        >保 存</el-button>
      </span>
    </el-dialog>

    <!-- 编辑药品列表 -->
    <el-dialog
      :title="label.name"
      :visible.sync="isShowMedicineModal"
      width="500px"
      height="100%"
      :before-close="hideMedicineModal"
      class="label-modal"
    >
      <DrugTreeCheck :medicinesKey="medicinesKey" ref="drugTree" @getCheckKey="getCheckKey"/>
      <span slot="footer" class="dialog-footer">
        <el-button @click="hideMedicineModal" size="small">取 消</el-button>
        <el-button type="primary" @click="saveMedicineModal" size="small">保 存</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { GetLabelList, GetMedicineListByLabelId, AddLabel, UpdateLabelName, DeleteLabel, UpdateMedicineDetail } from '@/api/dataMana'
import Pagination from '@/components/Pagination'
import DrugTreeCheck from './components/DrugTreeCheck'

export default {
  name: 'tagSetting',
  components: { Pagination, DrugTreeCheck },
  data() {
    return {
      labelPage: {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: 0,
        totalPage: 0
      },
      // 分页
      page: {
        pageSize: 15, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: '',
        totalPage: ''
      },

      // 查询标签名
      labelName: "",

      // 标签列表
      labelList: [],

      // 单个标签下的药品
      medicines: [],

      // 单个标签下的药品转换为tree选中的key数组
      medicinesKey: [],

      // 标签下是否存在药品提示信息
      medicinesMsg: "",

      // 修改的标签下的药品列表
      medicinesChange: [],

      // 点击查询的标签信息
      label: {},

      // 添加标签/编辑标签
      labelModal: {
        isShowLabelModal: false,
        title: "",
        data: {
          id: "",
          name: ""
        }
      },

      // 是否显示编辑药品列表弹框
      isShowMedicineModal: false
    }
  },
  created() {
    this.getLabelList();
  },
  methods: {
    // 获取标签列表
    async getLabelList() {
      const args = {
        pageNo: this.labelPage.pageNo,
        pageSize: this.labelPage.pageSize
      }
      const res = await GetLabelList(args)
      if (res.code === 0) {
        this.labelList = res.result
        this.labelPage = res.page || this.labelPage
      } else {
        this.$message.error(res.msg)
      }
    },

    // 获取单个标签下的药品
    async getMedicineList(searchKeys) {
      const page = {
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize
      };
      const args = Object.assign(page, searchKeys);
      const res = await GetMedicineListByLabelId(args);
      this.medicines = [];
      if (res.code === 0) {
        if (res.result.length > 0) {
          this.medicines = res.result;
          this.page = res.page;
          this.medicinesMsg = "success";
        } else {
          this.medicines = res.result;
          this.medicinesMsg = res.msg;
          this.page.totalCount = '';
          this.page.totalPage = '';
        }
      } else {
        this.$message.error(res.msg);
      }
    },

    // 点击列表，查看单个标签下的药品
    handleClick(data) {
      this.label = data;
      this.page.pageNo = 1;
      this.getMedicineList({
        id: this.label.id
      });
    },

    // 分页
    handlePage() {
      this.getMedicineList({
        id: this.label.id
      })
    },

    // 显示添加/编辑标签
    showLabelModal(type, data) {
      this.labelModal.isShowLabelModal = true;
      this.labelModal.title = type === 'add' ? '添加标签' : '编辑标签';
      this.labelModal.data = data;
    },

    // 关闭添加/编辑标签
    hideLabelModal() {
      this.labelModal.isShowLabelModal = false;
    },

    // 保存添加标签/编辑标签
    async saveLabelModal(type) {
      if (type === 'add') {
        const res = await AddLabel({
          name: this.labelModal.data.name
        });
        if (res.code === 0) {
          this.$message.success(res.msg);
          this.hideLabelModal();
          this.getLabelList();
        } else {
          this.$message.error(res.msg);
        }
      } else if (type === 'edit') {
        const res = await UpdateLabelName({
          id: this.labelModal.data.id,
          name: this.labelModal.data.name
        });
        if (res.code === 0) {
          this.$message.success(res.msg);
          this.hideLabelModal();
          this.getLabelList();
        } else {
          this.$message.error(res.msg);
        }

      }
    },

    // 删除标签
    async delLabel(id) {
      this.$confirm('是否删除该标签', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        const res = await DeleteLabel({
          id: id
        });
        if (res.code === 0) {
          this.$message.success(res.msg);
          this.getLabelList();
        } else {
          this.$message.error(res.msg);
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    // 编辑药品列表
    editMedicine() {
      console.log("edit");
      if (this.label.id) {
        this.medicinesKey = []
        if (this.medicines.length > 0) {
          this.medicines.map(item => {
            this.medicinesKey.push(item.medicineId);
          })
        }
      }
      this.isShowMedicineModal = true;
    },

    // 关闭编辑药品列表
    hideMedicineModal() {
      this.isShowMedicineModal = false;
    },

    // 保存编辑药品列表
    async saveMedicineModal() {
      const res = await UpdateMedicineDetail({
        labelId: this.label.id,
        medicineId: this.medicinesChange
      });
      if (res.code === 0) {
        this.$message.success(res.msg);
        this.isShowMedicineModal = false;
        this.handleClick(this.label);
      } else {
        this.$message.error(res.msg);
      }
    },
    // 获取勾选的节点数组
    getCheckKey(val) {
      this.medicinesChange = val;
    }

  },
  watch: {
    labelName() {
      if (this.labelName !== "") {
        this.getLabelList({
          name: this.labelName
        })
      } else {
        this.getLabelList();
      }
    }
  }
}
</script>
<style lang="less">
.drug-tag {
  height: 100%;
  .left-col {
    .box-card {
      .el-input.el-input--mini.el-input--suffix {
        width: 250px;
      }
      .el-button.el-button--primary.el-button--mini {
        margin-left: 10px;
      }
      .el-card__body {
        padding: 15px 0;
        .label-row {
          border-bottom: 1px dashed #ccc;
          padding: 10px 0;
          .label-name {
            padding-left: 30px;
            cursor: pointer;
          }
          .label-edit,
          .label-del {
            float: right;
            cursor: pointer;
          }
          .label-edit {
            padding-right: 15px;
            border-right: 1px dashed #ccc;
            color: #409eff;
          }
          .label-del {
            padding-left: 15px;
            padding-right: 30px;
            color: #f56c6c;
          }
        }
        .label-row:hover {
          background-color: rgb(235, 245, 255);
        }
        .label-row.is-active {
          background-color: rgb(235, 245, 255);
        }
        .label-row.el-row {
          font-size: 14px;
        }
      }
    }
  }
  .right-col {
    .box-card {
      .el-card__header {
        padding: 15px 20px;
      }
      .el-card__body {
        padding: 15px 0;
        .title-row {
          border-bottom: 1px dashed #ccc;
          padding: 0 0 21px 15px;
          text-align: center;
        }
        .label-row {
          border-bottom: 1px dashed #ccc;
          padding: 10px 0;
          font-size: 14px;
          .label-name {
            padding-left: 30px;
          }
        }
      }
    }
  }
  .label-modal {
    .el-input.el-input--small {
      width: 270px;
    }
  }
}
</style>

