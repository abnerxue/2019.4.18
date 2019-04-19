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
            <span class="f4">诊断列表</span>
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
            <span class="f4">适应症 </span>
            <template v-if="choose.label">
              <span class="f1 font-bold">【 {{choose.label}} 】</span>
              <el-button
                style="position: absolute; top: 15px; right: 20px;"
                type="primary"
                size="small"
                @click="handleSave"
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
                @handleSelect="handleAddPhysiology"
              />
              <el-table
                :data="indicationList.physiologyList"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
                class="list-table"
              >
                <el-table-column
                  prop="medicineName"
                  label="药品通用名"
                  align="center"
                  fixed
                  width="150"
                >
                </el-table-column>
                <el-table-column
                  prop="lactation"
                  label="哺乳期"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.lactation"
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
                  prop="liver_function"
                  label="肝功能-"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.liver_function"
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
                  prop="liver_function_uparrow"
                  label="肝功能-↑"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.liver_function_uparrow"
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
                  prop="kidney_function"
                  label="肾功能-"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.kidney_function"
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
                  prop="kidney_function_uparrow"
                  label="肾功能-↑"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.kidney_function_uparrow"
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
                @handleSelect="handleAddGroup"
              />
              <el-table
                :data="indicationList.groupList"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
                class="list-table"
              >
                <el-table-column
                  prop="medicineName"
                  label="药品通用名"
                  align="center"
                >
                </el-table-column>
                <el-table-column
                  prop="male"
                  label="男"
                  align="center"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.male"
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
                  prop="female"
                  label="女"
                  align="center"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.female"
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
                @handleSelect="handleAddAge"
              />
              <el-table
                :data="indicationList.ageList"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
                class="list-table"
              >
                <el-table-column
                  prop="medicineName"
                  label="药品通用名"
                  align="center"
                  fixed
                  width="150"
                >
                </el-table-column>
                <el-table-column
                  prop="under_3_month"
                  label="3月以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.under_3_month"
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
                  prop="under_6_month"
                  label="6月以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.under_6_month"
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
                  prop="under_2_year"
                  label="2岁以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.under_2_year"
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
                  prop="under_12_year"
                  label="12岁以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.under_12_year"
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
                  prop="under_16_year"
                  label="16岁以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.under_16_year"
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
                  prop="under_18_year"
                  label="18岁以下"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.under_18_year"
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
                  prop="above_60_year"
                  label="60岁以上"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.above_60_year"
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
                  prop="above_70_year"
                  label="70岁以上"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.above_70_year"
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
                @handleSelect="handleAddWeek"
              />
              <el-table
                :data="indicationList.gestationaList"
                border
                size="small"
                style="width: 100%;"
                :height="tableHeight"
                :header-cell-class-name="tableHeaderName"
                class="list-table"
              >
                <el-table-column
                  prop="medicineName"
                  label="药品通用名"
                  align="center"
                  fixed
                  width="150"
                >
                </el-table-column>
                <el-table-column
                  prop="alltime"
                  label="全程"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row.alltime"
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
                  prop="_0_12_week"
                  label="0~12周"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row._0_12_week"
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
                  prop="_12_childbirth"
                  label="12周~分娩"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row._12_childbirth"
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
                  prop="_27_childbirth"
                  label="27周~分娩"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row._27_childbirth"
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
                  prop="_34_childbirth"
                  label="34周~分娩"
                  align="center"
                  width="130"
                >
                  <template slot-scope="scope">
                    <el-select
                      v-model="scope.row._34_childbirth"
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
  GetAllIndicationByDiagnosisId,
  UpdatePhysiology,
  UpdateGroup,
  UpdateAge,
  UpdateWeek
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
      // 适应症列表
      indicationList: {
        gestationaList: [],
        groupList: [],
        ageList: [],
        physiologyList: []
      },
      oldIndicationList: {
        gestationaList: "",
        groupList: "",
        ageList: "",
        physiologyList: ""
      },
      // 查询的诊断id
      diagnosisId: '',
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
    let res = true;
    if (this.tabIndex === 0 && this.indicationList.physiologyList.length > 0) {
      res = this.oldIndicationList.physiologyList === JSON.stringify(this.indicationList.physiologyList);
    } else if (this.tabIndex === 1 && this.indicationList.groupList.length > 0) {
      res = this.oldIndicationList.groupList === JSON.stringify(this.indicationList.groupList);
    } else if (this.tabIndex === 2 && this.indicationList.ageList.length > 0) {
      res = this.oldIndicationList.ageList === JSON.stringify(this.indicationList.ageList);
    } else if (this.tabIndex === 3 && this.indicationList.gestationaList.length > 0) {
      res = this.oldIndicationList.gestationaList === JSON.stringify(this.indicationList.gestationaList);
    }
    if (res) {
      next();
    } else {
      return this.$confirm('当前页面未保存，是否确认离开？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        next();
      }).catch(() => {
        next(false);
      })
    }
  },
  methods: {
    // *诊断列表查询诊断
    handleSearch() {
      this.getAllList(this.searchKey)
    },

    // 保存
    handleSave() {
      if (this.tabIndex === 0) {
        // 病生理
        this.savePhysiology();
      } else if (this.tabIndex === 1) {
        // 人群
        this.saveGroup();
      } else if (this.tabIndex === 2) {
        // 年龄段
        this.saveAge();
      } else if (this.tabIndex === 3) {
        // 孕周
        this.saveWeek();
      }
    },
    // 删除行
    handleDelete(row) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.tabIndex === 0) {
          // 病生理
          let index = this.indicationList.physiologyList.indexOf(row);
          this.indicationList.physiologyList.splice(index, 1);
        } else if (this.tabIndex === 1) {
          // 人群
          let index = this.indicationList.groupList.indexOf(row);
          this.indicationList.groupList.splice(index, 1);
        } else if (this.tabIndex === 2) {
          // 年龄段
          let index = this.indicationList.ageList.indexOf(row);
          this.indicationList.ageList.splice(index, 1);
        } else if (this.tabIndex === 3) {
          // 孕周
          let index = this.indicationList.gestationaList.indexOf(row);
          this.indicationList.gestationaList.splice(index, 1);
        }
      }).catch(() => { })
    },
    // 增加病生理
    async handleAddPhysiology(val) {
      if (this.indicationList.physiologyList.length > 0) {
        let isHave = false;
        this.indicationList.physiologyList.map(item => {
          if (item.medcineId == val.id) {
            this.$message.warning('该药品已添加');
            isHave = true;
          }
        })
        if (isHave == false) {
          this.indicationList.physiologyList.push({
            diagnosisId: this.diagnosisId,
            medicineName: val.name,
            medcineId: val.id,
            lactation: 0,
            liver_function: 0,
            liver_function_uparrow: 0,
            kidney_function: 0,
            kidney_function_uparrow: 0
          }) 
        }
      } else {
        this.indicationList.physiologyList = [{
          diagnosisId: this.diagnosisId,
          medicineName: val.name,
          medcineId: val.id,
          lactation: 0,
          liver_function: 0,
          liver_function_uparrow: 0,
          kidney_function: 0,
          kidney_function_uparrow: 0
        }]
      }
    },
    // 保存病生理
    async savePhysiology() {
      const res = await UpdatePhysiology({
        diagnosisId: this.diagnosisId,
        phyList: this.indicationList.physiologyList
      });
      if (res.code === 0) {
        this.$message.success(res.msg);
        this.getIndicationList(this.diagnosisId);
      } else {
        this.$message.error(res.msg);
      }
    },
    // 增加人群
    async handleAddGroup(val) {
      if (this.indicationList.groupList.length > 0) {
        let isHave = false;
        this.indicationList.groupList.map(item => {
          if (item.medcineId == val.id) {
            this.$message.warning('该药品已添加');
            isHave = true;
          }
        })
        if (isHave == false) {
          this.indicationList.groupList.push({
            diagnosisId: this.diagnosisId,
            medicineName: val.name,
            medcineId: val.id,
            male: 0,
            female: 0
          }) 
        }
      } else {
        this.indicationList.groupList = [{
          diagnosisId: this.diagnosisId,
          medicineName: val.name,
          medcineId: val.id,
          male: 0,
          female: 0
        }]
      }
    },
    // 保存人群
    async saveGroup() {
      const res = await UpdateGroup({
        diagnosisId: this.diagnosisId,
        groupList: this.indicationList.groupList
      });
      if (res.code === 0) {
        this.$message.success(res.msg);
        this.getIndicationList(this.diagnosisId);
      } else {
        this.$message.error(res.msg);
      }
    },
    // 增加年龄段
    async handleAddAge(val) {
      if (this.indicationList.ageList.length > 0) {
        let isHave = false;
        this.indicationList.ageList.map(item => {
          if (item.medcineId == val.id) {
            this.$message.warning('该药品已添加');
            isHave = true;
          }
        })
        if (isHave == false) {
          this.indicationList.ageList.push({
            medcineId: val.id,
            medicineName: val.name,
            diagnosisId: this.diagnosisId,
            under_3_month: 0,
            under_6_month: 0,
            under_2_year: 0,
            under_12_year: 0,
            under_16_year: 0,
            under_18_year: 0,
            above_60_year: 0,
            above_70_year: 0
          }) 
        }
      } else {
        this.indicationList.ageList = [{
          medcineId: val.id,
          medicineName: val.name,
          diagnosisId: this.diagnosisId,
          under_3_month: 0,
          under_6_month: 0,
          under_2_year: 0,
          under_12_year: 0,
          under_16_year: 0,
          under_18_year: 0,
          above_60_year: 0,
          above_70_year: 0
        }]
      }
    },
    // 保存年龄段
    async saveAge() {
      const res = await UpdateAge({
        diagnosisId: this.diagnosisId,
        ageList: this.indicationList.ageList
      });
      if (res.code === 0) {
        this.$message.success(res.msg);
        this.getIndicationList(this.diagnosisId);
      } else {
        this.$message.error(res.msg);
      }
    },
    // 增加孕周
    async handleAddWeek(val) {
      if (this.indicationList.gestationaList.length > 0) {
        let isHave = false;
        this.indicationList.gestationaList.map(item => {
          if (item.medcineId == val.id) {
            this.$message.warning('该药品已添加');
            isHave = true;
          }
        })
        if (isHave == false) {
          this.indicationList.gestationaList.push({
            medcineId: val.id,
            medicineName: val.name,
            diagnosisId: this.diagnosisId,
            alltime: 0,
            _0_12_week: 0,
            _12_childbirth: 0,
            _27_childbirth: 0,
            _34_childbirth: 0
          }) 
        }
      } else {
        this.indicationList.gestationaList = [{
          medcineId: val.id,
          medicineName: val.name,
          diagnosisId: this.diagnosisId,
          alltime: 0,
          _0_12_week: 0,
          _12_childbirth: 0,
          _27_childbirth: 0,
          _34_childbirth: 0
        }]
      }
    },
    // 保存孕周
    async saveWeek() {
      const res = await UpdateWeek({
        diagnosisId: this.diagnosisId,
        weekList: this.indicationList.gestationaList
      });
      if (res.code === 0) {
        this.$message.success(res.msg);
        this.getIndicationList(this.diagnosisId);
      } else {
        this.$message.error(res.msg);
      }
    },
    beforeLeave(tab) {
      let res = true;
      if (this.tabIndex === 0 && this.indicationList.physiologyList.length > 0) {
        res = this.oldIndicationList.physiologyList === JSON.stringify(this.indicationList.physiologyList);
      } else if (this.tabIndex === 1 && this.indicationList.groupList.length > 0) {
        res = this.oldIndicationList.groupList === JSON.stringify(this.indicationList.groupList);
      } else if (this.tabIndex === 2 && this.indicationList.ageList.length > 0) {
        res = this.oldIndicationList.ageList === JSON.stringify(this.indicationList.ageList);
      } else if (this.tabIndex === 3 && this.indicationList.gestationaList.length > 0) {
        res = this.oldIndicationList.gestationaList === JSON.stringify(this.indicationList.gestationaList);
      }
      if (res) {
        this.tabIndex = Number(tab);
      } else {
        return this.$confirm('当前页面未保存，是否确认离开？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.tabIndex = Number(tab);
          this.getIndicationList(this.diagnosisId);
        }).catch(() => {
          console.log('cancle')
          throw new Error('cancle')
        })
      }
    },
    // *点击 表格
    handleClickRow(row) {
      let res = true;
      if (this.tabIndex === 0 && this.indicationList.physiologyList.length > 0) {
        res = this.oldIndicationList.physiologyList === JSON.stringify(this.indicationList.physiologyList);
      } else if (this.tabIndex === 1 && this.indicationList.groupList.length > 0) {
        res = this.oldIndicationList.groupList === JSON.stringify(this.indicationList.groupList);
      } else if (this.tabIndex === 2 && this.indicationList.ageList.length > 0) {
        res = this.oldIndicationList.ageList === JSON.stringify(this.indicationList.ageList);
      } else if (this.tabIndex === 3 && this.indicationList.gestationaList.length > 0) {
        res = this.oldIndicationList.gestationaList === JSON.stringify(this.indicationList.gestationaList);
      }
      if (res) {
        this.choose = {
          id: row.id,
          label: row.diagnosisName
        }
        this.currentRow = row;
        this.getIndicationList(row.id);
      } else {
        return this.$confirm('当前页面未保存，是否确认离开？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.choose = {
            id: row.id,
            label: row.diagnosisName
          };
          this.currentRow = row;
          this.getIndicationList(row.id);
        }).catch(() => {
          this.$refs.table.setCurrentRow(this.currentRow)
        })
      }
    },
    // *获取适应症列表
    async getIndicationList(id) {
      this.diagnosisId = id;
      const res = await GetAllIndicationByDiagnosisId({
        diagnosisId: id
      });
      if (res.code === 0) {
        this.indicationList = res.result;
        this.oldIndicationList = {
          gestationaList: JSON.stringify(this.indicationList.gestationaList),
          groupList: JSON.stringify(this.indicationList.groupList),
          ageList: JSON.stringify(this.indicationList.ageList),
          physiologyList: JSON.stringify(this.indicationList.physiologyList)
        }
      }
    },
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

