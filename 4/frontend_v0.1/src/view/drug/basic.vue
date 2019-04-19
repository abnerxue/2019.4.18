/* <template>
  <div class="drug-basic" style="height: 100%">
    药品基本信息管理
  </div>
</template>

<script>

export default {
  name: '',
  components: {},
  data() {
    return {}
  },
  methods: {}
}
</script> */
<template>
  <div style="height: 100%">
    <el-form
      :inline="true"
      :model="form"
      label-width="100px"
      size="small"
    >
      <el-form-item label="">
         <el-input
              size="mini"
              placeholder="查找药品"
              suffix-icon="el-icon-search"
              v-model="labelName"
            >
            </el-input>
      </el-form-item>
      <el-form-item>
        <el-button
        style='margin-left:2200%;'
          type="primary"
          @click="handleSearch"
        >上传</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="24" style="height: 100%;width:100%;">
      <el-col :span="5" style="height: 100%;">
        <DrugTree 
          ref="drugTree"
          :type="'org'"
          @updateChoose="updateChoose"
        />
      </el-col>
      <el-col :span="19">
         <el-button
        style='margin-left:2%;'
          type="primary"
          @click="handleSearch"
        >+添加药品</el-button> 
        <el-table
          :data="list"
          border
          size="small"
          :row-class-name="tableRowClassName"
          :header-cell-class-name="tableHeaderName"
          style="width: 100%;"
        >
          <el-table-column
            v-for="(item,index) in positionKey"
            :key="index"
            :prop="item.prop"
            :label="item.label"
            :width="item.width"
            align="center"
          ></el-table-column>
           <el-table-column prop="enable" label="操作" align="center" width="100">
        <template slot-scope="scope">
          <i @click="showEdit(scope.row.liverKidneyDicId)" class="el-icon-edit pointer"></i>
          <span class="d-inline-block" style="width: 8px;"></span>
          <i
            @click="handleDelete(scope.row.liverKidneyDicId)"
            class="el-icon-delete text-red pointer"
          ></i>
        </template>
      </el-table-column>
        </el-table>
        <Pagination
          class="text-center"
          :page="page"
          @handlePageChange="handlePage"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { Allmedcinehos } from '@/api/dataMana'
import { Allhospital } from '@/api/dictionaries'
import { skinTest } from '@/global/cfg'
import Pagination from '@/components/Pagination'
import SearchInfo from './components/SearchInfo'
import DrugTree from './components/DrugTree'

export default {
  name: 'dataManaOrg',
  components: { SearchInfo, Pagination, DrugTree },
  watch: {
    list() {
      this.arr = []
    }
  },
  data() {
    return {
      arr: [],
      page: {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: '',
        totalPage: ''
      },
      oldList: [],
      list: [],
      positionKey: [
        {
          prop: 'name',
          width: '',
          label: '通用名'
        },
        {
          prop: 'typeName',
          width: '',
          label: '中/西/中成药'
        },
        {
          prop: 'categoryName',
          width: '',
          
          label: '大分类'
        },
        {
          prop: 'subcategoryName',
          width: '',
          label: '小分类'
        },
        {
          prop: 'doseRecommendPerTime',
          width: '',
          label: '每次推荐量'
        },
        {
          prop: 'doseRecommendPerDay',
          width: '',
          label: '每日推荐量'
        },
        {
          prop: 'dosageFormName',
          width: '',
          label: '剂型'
        },
        {
          prop: 'routeOfMedication',
          width: '',
          label: '给药途径'
        },
        {
          prop: 'frequency',
          width: '',
          label: '给药频率'
        },
        {
          prop: 'totalDose',
          width: '',
          label: '疗程总剂量'
        },
        {
          prop: 'maxDuration',
          width: '',
          label: '最长给药持续时间'
        },
        {
          prop: 'maxMultidaysDose',
          width: '',
          label: '最大超多日用量'
        },
        {
          prop: 'skinTest',
          width: '',
          label: '皮试要求'
        },
        
      ],

      allhospital: [], // 医院

      form: {
        standardSource: '',
        subcategory: ''
      }
    }
  },
  async created() {
    this.getList()
    await this.getAllHospital() // 医院
  },
  beforeRouteLeave(to, from, next) {
    const res = this.oldList === JSON.stringify(this.list)
    if (!res) {
      this.$confirm('当前页面未保存，是否确认离开？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => { next() })
        .catch(() => { next(false) })
    } else {
      next()
    }
  },
  methods: {
    updateChoose(val) {
      if (val.id === 0) {
        this.getList();
      } else {
        this.form.subcategory = val.id;
        this.getList(this.form);
      }
    },
      async showEdit(id) {
      const res = await GetCheckItemsDetail({ liverKidneyDicId: id })
      if (res.code === 0) {
        this.detail = res.result[0]
        this.$refs.modal.isShowModal = true
        this.$refs.modal.getCheckItemsList()
        if (this.tabIndex === 0) {
          this.$refs.modal.getLiverList()
        } else if (this.tabIndex === 1) {
          this.$refs.modal.getDneydicList()
        }
      } else {
        this.$message.error(res.msg)
      }
    },
    // 点击删除
    handleDelete(ruleName) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.delete(ruleName)
      }).catch(() => { })
    },
    async delete(id) {
      const res = await DelateCheckLimit({ liverKidneyDicId: id })
      if (res.code === 0) {
        this.$message.success('删除成功!')
        this.$emit('getChecklimitList')
      } else {
        this.$message.error(res.msg)
      }
    },
    // 

    async getAllHospital() {
      const res = await Allhospital()
      if (res.code === 0) {
        this.allhospital = [
          {
            id: '',
            unitName: '全部'
          },
          ...res.result
        ]
      }
    },

    handleSearch() {
      this.page = {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: '',
        totalPage: ''
      };
      this.getList(this.form);  
    },

    checkRow(checked, { id }, index) {
      this.$set(this.list[index], 'needAudit', checked)
      const data = this.arr.find(item => item.id === id)
      if (data) data.needAudit = Number(checked)
      else this.arr.push({ id, needAudit: Number(checked) })
    },
    handlePage() {
      // const res = this.oldList === JSON.stringify(this.list)
      // if (!res) {
      //   this.$confirm('当前页面未保存，是否确认离开？', '提示', {
      //     confirmButtonText: '确定',
      //     cancelButtonText: '取消',
      //     type: 'warning'
      //   }).then(() => {
      //     const searchKeys = this.$refs.search.form
      //     this.getList(searchKeys)
      //   }).catch(() => { })
      // } else {
      //   const searchKeys = this.$refs.search.form
      //   this.getList(searchKeys)
      // }
    //   const searchKeys = this.$refs.search.form
      this.getList(this.form);
    },
    async getList(searchKeys) {
      const page = {
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize
      }
      const args = Object.assign(page, searchKeys)
      const res = await Allmedcinehos(args)
      if (res.code === 0) {
        this.list = res.result
        this.page = res.page || this.page
        this.list.map(item => Object.assign(item, {
          needAudit: Boolean(item.needAudit),
          skinTest: skinTest.filter(step => item.skinTest === step.id)[0].desp
        }))
        this.oldList = JSON.stringify(this.list)
      } else {
        this.$message.error(res.msg)
      }
    },
    // 修改tablerow的背景色
    tableRowClassName({ row, rowIndex }) {
      if ((rowIndex % 2) === 0) {
        return 'row1'
      }
      return 'row2'
    },
    // 修改table header的背景色
    tableHeaderName({ row, rowIndex }) {
      return 'table-header'
    }
  }
}
</script>

