<template>
  <div class="drug-paas">
    <el-row :gutter="20" style="height: 100%;">
      <el-col :span="9" style="height: 100%;">
        <DrugTree 
          ref="drugTree"
          :type="'paas'"
          @updateChoose="updateChoose"
        />
      </el-col>
      <el-col :span="15">
        <el-table
          :data="list"
          border
          size="small"
          :row-class-name="tableRowClassName"
          :header-cell-class-name="tableHeaderName"
          style="width: 100%;"
          @expand-change="getMoreList"
        >
          <el-table-column
            v-for="(item,index) in positionKey"
            :key="index"
            :prop="item.prop"
            :label="item.label"
            :width="item.width"
            align="center"
          ></el-table-column>
          <el-table-column
            type="expand"
            fixed="right"
            label="选为平台标准"
            width="90"
            align="center"
          >
            <template slot-scope="props">
              <el-radio-group
                v-model="changeId"
                @change="handleChange"
                style="width: 100%"
              >
                <el-table
                  :data="moreList"
                  border
                  size="small"
                  style="width: 100%;"
                  class="expand-table"
                >
                  <el-table-column
                    v-for="(item,index) in positionKey"
                    :key="index"
                    :prop="item.prop"
                    :label="item.label"
                    :width="item.width"
                    align="center"
                  ></el-table-column>
                  <el-table-column
                    fixed="right"
                    label="选为平台标准"
                    width="90"
                    align="center"
                  >
                    <template slot-scope="scope">
                      <el-radio :label="scope.row.id"></el-radio>
                    </template>
                  </el-table-column>
                </el-table>
              </el-radio-group>
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
import { Allmedcine, Medcinebyid, SaveMedcine } from '@/api/dataMana'
import { skinTest } from '@/global/cfg'
import Pagination from '@/components/Pagination'
import SearchInfo from './components/SearchInfo'
import DrugTree from './components/DrugTree'

export default {
  name: 'dataManaPaas',
  components: { SearchInfo, Pagination, DrugTree },
  data() {
    return {
      page: {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: '',
        totalPage: ''
      },
      list: [],
      positionKey: [
        {
          prop: 'name',
          width: '',
          label: '通用名'
        },
        {
          prop: 'unitName',
          width: '',
          label: '标准来源'
        },
        {
          prop: 'doseRecommendPerTimeMin',
          width: '',
          label: '最小次用量'
        },
        {
          prop: 'doseRecommendPerTimeMax',
          width: '',
          label: '最大次推荐量'
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
          prop: 'frequencyMin',
          width: '',
          label: '最小给药频率'
        },
        {
          prop: 'frequencyMax',
          width: '',
          label: '最大给药频率'
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
        {
          prop: 'mentalStupefacientToxicName',
          width: '',
          label: '精麻毒药品判定'
        }
      ],

      // 展开行列表
      moreList: [],

      // 展开行修改
      moreChange: [],

      changeId: "",

      // 查询列表subcategory
      subcategory: ""
    }
  },
  created() {
    this.getList()
  },
  methods: {
    updateChoose(val) {
      if (val.id === 0) {
        this.getList();
      } else {
        this.subcategory = val.id;
        this.getList({
          subcategory: this.subcategory
        });
      }
    },

    handlePage() {
      this.getList({
        subcategory: this.subcategory
      })
    },

    // 展开获取更多信息
    async getMoreList(row, expandedRows) {
      this.moreList = [];
      this.changeId = "";
      if (expandedRows.length > 1) {
        expandedRows.shift()
      }
      const res = await Medcinebyid({
        name: row.name,
        specification: row.specification
      })
      if (res.code === 0) {
        this.moreList = res.result
        this.moreList.map(item => Object.assign(item, {
          platformStandard: Boolean(item.platformStandard),
          skinTest: skinTest.filter(step => item.skinTest === step.id)[0].desp
        }))
        if (this.moreList.length > 0) {
          this.moreList.map(item => {
            if (item.platformStandard == 1) {
              this.changeId = item.id
            }
          })
        }
      } else {
        this.$message.error(res.msg)
      }
    },

    // 更新平台标准
    async handleSave() {
      const res = await SaveMedcine(JSON.stringify(this.moreChange));
      if (res.code === 0) {
        this.getList()
        this.$message.success(res.msg)
      } else {
        this.$message.error(res.msg)
      }
    },

    handleChange(value) {
      this.moreChange = [];
      this.moreList.map(item => {
        if (item.id == this.changeId) {
          item.platformStandard = 1;
        } else {
          item.platformStandard = 0;
        }
        this.moreChange.push({
          id: item.id,
          platformStandard: item.platformStandard
        })
      })
      this.handleSave();
    },

    // 获取全部信息
    async getList(searchKeys) {
      const page = {
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize
      }
      const args = Object.assign(page, searchKeys)
      const res = await Allmedcine(args)
      if (res.code === 0) {
        this.list = res.result
        this.page = res.page || this.page
        this.list.map(item => Object.assign(item, {
          needAudit: Boolean(item.platformStandard),
          skinTest: skinTest.filter(step => item.skinTest === step.id)[0].desp
        }))
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
<style lang="less">
.drug-paas {
  height: 100%;
  .el-table__expanded-cell {
    padding: 0 !important;
  }
  .expand-table {
    .el-table__header-wrapper {
      display: none;
    }
    .cell {
      .el-radio__label {
        display: none;
      }
    }
  }
}
</style>
