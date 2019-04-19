 <template>
  <div style="height: 100%" class="rule-adaptation">
    <!-- 适应症限用 -->
    <el-row
      :gutter="20"
      style="height: 100%"
    >
      <el-col
        :span="10"
        style="height: 100%"
      >
        <el-card class="box-card">
          <div
            slot="header"
            class="clearfix"
          >
            <span>诊断列表</span>
          </div>
          <div>
            <el-input
              v-model="searchKey"
              size="small"
              placeholder="查找诊断"
              clearable
              @change="handleSearch"
            >
            </el-input>
            <div class="space"></div>
            <el-table
              ref="table"
              :data="list"
              @row-click="handleClickRow"
              highlight-current-row
              border
              size="small"
              style="width: 100%;"
              :height="tableHeight"
              :header-cell-class-name="tableHeaderName"
            >
              <el-table-column
                prop="icd10"
                label="编码"
                align="center"
                width="100"
              ></el-table-column>
              <el-table-column
                prop="diagnosisName"
                label="名称"
                align="center"
              ></el-table-column>
            </el-table>
            <Pagination
              class="text-center"
              :page="page"
              @handlePageChange="getAllList"
            />
          </div>
        </el-card>
      </el-col>
      <el-col
        :span="14"
        style="height: 100%"
      >
        <el-card class="box-card">
          <div
            slot="header"
            class="clearfix"
          >
            <span>适应症 </span>
            <template v-if="choose.label">
              <span class="f1 font-bold">【 {{choose.label}} 】</span>
              <el-button
                style="position: absolute; top: 15px; right: 20px;"
                type="primary"
                size="small"
                @click=""
                :loading="loading"
              >保存</el-button>
            </template>
          </div>
          <div
            class="f4"
            v-if="!choose.label"
          >请先选择药品</div>
          <el-tabs
            v-if="choose.label"
            :before-leave="beforeLeave"
          >
            <el-tab-pane label="病生理">
              <AutoSearch
                :placeHolder="'添加药品'"
                :list="searchList"
                @handleSelect="handleAdd"
              />
              <el-table
                :data="pathologyList"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
                class="list-table"
              >
                <el-table-column
                  prop="name"
                  label="药品通用名"
                  align="center"
                  fixed
                  width="150"
                >
                </el-table-column>
                <el-table-column
                  prop="type1"
                  label="哺乳期"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type1"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type2"
                  label="肝功能-"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type2"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type3"
                  label="肝功能-↑"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type3"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type4"
                  label="肾功能-"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type4"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type5"
                  label="肾功能-↑"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type5"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="enable"
                  label="操作"
                  align="center"
                  width="50"
                >
                  <template slot-scope="scope">
                    <i
                      @click="handleDelete(scope.row)"
                      class="el-icon-delete text-red pointer"
                    ></i>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="人群">
              <AutoSearch
                :placeHolder="'添加药品'"
                :list="searchList"
                @handleSelect="handleAdd"
              />
              <el-table
                :data="peopleList"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
                class="list-table"
              >
                <el-table-column
                  prop="name"
                  label="药品通用名"
                  align="center"
                >
                </el-table-column>
                <el-table-column
                  prop="type1"
                  label="男"
                  align="center"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type1"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type2"
                  label="女"
                  align="center"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type2"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="enable"
                  label="操作"
                  align="center"
                  width="50"
                >
                  <template slot-scope="scope">
                    <i
                      @click="handleDelete(scope.row)"
                      class="el-icon-delete text-red pointer"
                    ></i>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="年龄段">
              <AutoSearch
                :placeHolder="'添加药品'"
                :list="searchList"
                @handleSelect="handleAdd"
              />
              <el-table
                :data="ageList"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
                class="list-table"
              >
                <el-table-column
                  prop="name"
                  label="药品通用名"
                  align="center"
                  fixed
                  width="150"
                >
                </el-table-column>
                <el-table-column
                  prop="type1"
                  label="3月以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type1"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type2"
                  label="6月以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type2"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type3"
                  label="2岁以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type3"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type4"
                  label="12岁以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type4"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type5"
                  label="16岁以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type5"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type6"
                  label="18岁以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type6"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type7"
                  label="60岁以上"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type7"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type8"
                  label="70岁以上"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type8"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="enable"
                  label="操作"
                  align="center"
                  width="50"
                >
                  <template slot-scope="scope">
                    <i
                      @click="handleDelete(scope.row)"
                      class="el-icon-delete text-red pointer"
                    ></i>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="孕周">
              <AutoSearch
                :placeHolder="'添加药品'"
                :list="searchList"
                @handleSelect="handleAdd"
              />
              <el-table
                :data="pregnancyList"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
                class="list-table"
              >
                <el-table-column
                  prop="name"
                  label="药品通用名"
                  align="center"
                  fixed
                  width="150"
                >
                </el-table-column>
                <el-table-column
                  prop="type1"
                  label="全程"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type1"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type2"
                  label="0~12周"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type2"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type3"
                  label="12周~分娩"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type3"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type4"
                  label="27周~分娩"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type4"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="type5"
                  label="34周~分娩"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.type5"
                      placeholder="请选择"
                      size="mini"
                    >
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column
                  prop="enable"
                  label="操作"
                  align="center"
                  width="50"
                >
                  <template slot-scope="scope">
                    <i
                      @click="handleDelete(scope.row)"
                      class="el-icon-delete text-red pointer"
                    ></i>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  SelectAllDiagnosis,
  Allmedcinefold,
} from '@/api/dataMana'
import Pagination from '@/components/Pagination'
import AutoSearch from '@/components/AutoSearch'

export default {
  name: 'adaptation',
  components: { AutoSearch, Pagination },
  data() {
    return {
      tableHeight: 50,
      loading: false,
      searchKey: '', // 查询诊断 关键字
      list: [], // 诊断信息
      page: {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: 0,
        totalPage: 0
      },
      choose: {
        id: '',
        label: ''
      },
      tabIndex: 0,
      tableData: [],
      searchList: [],
      // 病生理列表
      pathologyList: [
        {
          name: '急性阿米巴病或肠道阿米巴病NOS',
          type1: 0,
          type2: 0,
          type3: 0,
          type4: 0,
          type5: 0
        }
      ],
      // 人群列表
      peopleList: [
        {
          name: '急性阿米巴病或肠道阿米巴病NOS',
          type1: 0,
          type2: 0
        }
      ],
      // 年龄段列表
      ageList: [
        {
          name: '急性阿米巴病或肠道阿米巴病NOS',
          type1: 0,
          type2: 0,
          type3: 0,
          type4: 0,
          type5: 0,
          type6: 0,
          type7: 0,
          type8: 0
        }
      ],
      // 孕期列表
      pregnancyList: [
        {
          name: '急性阿米巴病或肠道阿米巴病NOS',
          type1: 0,
          type2: 0,
          type3: 0,
          type4: 0,
          type5: 0
        }
      ],
      // 选项
      options: [
        {
          value: 0,
          label: '无'
        },
        {
          value: 1,
          label: '提醒'
        },
        {
          value: 2,
          label: '人工审核'
        },
        {
          value: 3,
          label: '打回'
        }
      ]
    }
  },
  created() {
    this.getAllMedcine()
    this.getAllList()
  },
  mounted() {
    setTimeout(() => {
      this.tableHeight = window.innerHeight - 350
    }, 100)
  },
  beforeRouteLeave(to, from, next) {
    let res = true
    if (this.tabIndex === 0 && this.tableData.length > 0) {
      res = this.oldLimitList === JSON.stringify(this.tableData)
    } else if (this.tabIndex === 1 && this.treatment.id > 0) {
      res = this.oldTreatment === JSON.stringify(this.treatment)
    }
    if (res) {
      next()
    } else {
      this.$confirm('当前页面未保存，是否确认离开？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => { next() })
        .catch(() => { next(false) })
    }
  },
  methods: {
    // *诊断列表查询诊断
    handleSearch() {
      this.getAllList(this.searchKey)
    },

    // 增加
    async handleAdd(val) {
    //   const obj = {
    //     diagnosisId: this.choose.id,
    //     medcineId: val.id,
    //     action: ''
    //   }
    //   const res = await AddLimit(obj)
    //   if (res.code === 0) {
    //     this.getSelectLimit()
    //   } else {
    //     this.$message.error(res.msg)
    //   }
    },
    beforeLeave(tab) {
      this.tabIndex = Number(tab);
      console.log('离开tab时调用' + this.tabIndex);
    //   let res = true
    //   if (this.tabIndex === 0 && this.tableData.length > 0) {
    //     res = this.oldLimitList === JSON.stringify(this.tableData)
    //   } else if (this.tabIndex === 1 && this.treatment.id > 0) {
    //     res = this.oldTreatment === JSON.stringify(this.treatment)
    //   }
    //   if (res) {
    //     this.tabIndex = Number(tab)
    //     this.handleData(this.tabIndex)
    //   } else {
    //     return this.$confirm('当前页面未保存，是否确认离开？', '提示', {
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     }).then(() => {
    //       this.tabIndex = Number(tab)
    //       this.handleData(this.tabIndex)
    //     }).catch(() => {
    //       console.log('cancle')
    //       throw new Error('cancle')
    //     })
    //   }
    },
    // *点击 表格
    handleClickRow(row) {
      let res = true
      if (this.tabIndex === 0 && this.tableData.length > 0) {
        res = this.oldLimitList === JSON.stringify(this.tableData)
      } else if (this.tabIndex === 1 && this.treatment.id > 0) {
        res = this.oldTreatment === JSON.stringify(this.treatment)
      }
      if (res) {
        this.choose = {
          id: row.id,
          label: row.diagnosisName
        }
        this.treatment = {}
        this.handleData(this.tabIndex)
        this.currentRow = row
      } else {
        return this.$confirm('当前页面未保存，是否确认离开？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.choose = {
            id: row.id,
            label: row.diagnosisName
          }
          this.treatment = {}
          this.handleData(this.tabIndex)
          this.currentRow = row
        }).catch(() => {
          this.$refs.table.setCurrentRow(this.currentRow)
        })
      }
    },
    // *判断 tab 调用 不同的接口
    handleData(tabIndex) {
      if (tabIndex === 0) {
        // 病生理
        console.log(tabIndex);
      } else if (tabIndex === 1) {
        // 人群
        console.log(tabIndex);
      } else if (tabIndex === 2) {
        // 年龄段
        console.log(tabIndex);
      } else if (tabIndex === 3) {
        // 孕周
        console.log(tabIndex);
      }
    },
    // // 点击删除
    // handleDelete(row) {
    //   this.$confirm('确定删除吗?', '提示', {
    //     confirmButtonText: '确定',
    //     cancelButtonText: '取消',
    //     type: 'warning'
    //   }).then(() => {
    //     this.deleteLimit(row)
    //   }).catch(() => { })
    // },
    // // 删除 限用药物
    // async deleteLimit(row) {
    //   const res = await DeleteLimit({ id: row.id })
    //   if (res.code === 0) {
    //     this.$message.success('删除成功!')
    //     this.getSelectLimit(row.diagnosisId)
    //   } else {
    //     this.$message.error(res.msg)
    //   }
    // },
    // // 保存 限用药物
    // async handleSave() {
    //   this.loading = true
    //   if (this.tabIndex === 0) {
    //     const args = JSON.stringify(this.tableData)
    //     const res = await SaveLimit(args)
    //     this.loading = false
    //     if (res.code === 0) {
    //       this.$message.success(res.msg)
    //       this.getSelectLimit()
    //     } else {
    //       this.$message.error(res.msg)
    //     }
    //   } else if (this.tabIndex === 1) {
    //     const res = await UpdatTreatment(this.treatment)
    //     this.loading = false
    //     if (res.code === 0) {
    //       this.$message.success(res.msg)
    //       this.getSelectTreatment()
    //     } else {
    //       this.$message.error(res.msg)
    //     }
    //   }
    // },
    // // 处理枚举 - 药品名称
    // handleEnumMedcine(id) {
    //   return this.allMedcine.filter(item => id === item.id)[0].name
    // },
    // // 获取 限用药品及等级设置
    // async getSelectLimit() {
    //   const res = await SelectLimit({ diagnosisId: this.choose.id })
    //   if (res.code === 0) {
    //     this.tableData = res.result
    //     this.oldLimitList = JSON.stringify(res.result)
    //   }
    // },
    // // 获取疗程
    // async getSelectTreatment() {
    //   const res = await SelectTreatment({ diagnosisId: this.choose.id })
    //   if (res.code === 0) {
    //     this.treatment = res.result[0] || this.treatment
    //     this.oldTreatment = JSON.stringify(this.treatment)
    //   }
    // },
    // *获取诊断信息
    async getAllList(key) {
      const args = {
        filter: key,
        pageNo: this.page.pageNo,
        pageSize: this.page.pageSize
      }
      const res = await SelectAllDiagnosis(args)
      if (res.code === 0) {
        this.list = res.result
        this.page = res.page || this.page
      }
    },
    // *获取药品信息
    async getAllMedcine() {
      const res = await Allmedcinefold()
      if (res.code === 0) {
        this.allMedcine = res.result
        this.searchList = JSON.parse(JSON.stringify(this.allMedcine))
        this.searchList.forEach((item) => { Object.assign(item, { value: item.name }) })
      }
    },
    // *修改table header的背景色
    tableHeaderName({ row, rowIndex }) {
      return 'table-header'
    }
  }
}
</script>
<style lang="less">
.rule-adaptation {
  .list-table {
    margin-top: 10px;
  }
}
</style>

