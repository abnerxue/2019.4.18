<template>
  <div style="height: 100%">
    <!-- 配伍管理 -->
    <el-row :gutter="20" style="height: 100%">
      <el-col :span="9" style="height: 100%">
        <el-card class="box-card" style="height: 100%; overflow-y: auto;">
          <div slot="header" class="clearfix" style="height: 100%;">
            <span class="f4">药品与标签列表</span>
          </div>
          <div>
            <el-tabs @tab-click="clickTab">
              <el-tab-pane label="药品列表">
                <Drugs :type="'all'" @updateChoose="updateChoose"/>
              </el-tab-pane>
              <el-tab-pane label="标签列表">
                <el-input
                  v-model="searchKey"
                  size="small"
                  placeholder="查找标签"
                  clearable
                  @change="handleSearch"
                ></el-input>
                <div class="space"></div>
                <el-table
                  ref="tableTags"
                  :data="labelList"
                  @row-click="handleClickRow"
                  highlight-current-row
                  border
                  size="small"
                  style="width: 100%;"
                  :header-cell-class-name="tableHeaderName"
                >
                  <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
                  <el-table-column prop="name" label="名称" align="center"></el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </div>
        </el-card>
      </el-col>
      <el-col :span="15" style="height: 100%">
        <el-card class="box-card" style="height: 100%">
          <div slot="header" class="clearfix">
            <span>添加规则</span>
            <template v-if="choose.name">
              <span class="f1 font-bold">【 {{choose.name}} 】</span>
              <el-button
                style="position: absolute; top: 15px; right: 20px;"
                type="primary"
                size="small"
                @click="handleSave"
                :loading="loading"
              >保存</el-button>
            </template>
          </div>
          <div class="f4" v-if="!choose.name">请先选择药品</div>
          <div v-if="choose.name">
            <AutoSearch :placeHolder="'添加药品、种类或标签'" :list="searchList" @handleSelect="handleAdd"/>
            <div class="space"></div>
            <el-tabs @tab-click="clickTab2">
              <el-tab-pane label="配伍禁忌">
                <el-table :data="compatibility" border size="small" style="width: 100%;">
                  <el-table-column prop="medcineId" label="药物B名称">
                    <template slot-scope="scope">{{handleEnumMedcine(scope.row.medcineId)}}</template>
                  </el-table-column>
                  <el-table-column prop="reactionDescription" label="作用机制与结果">
                    <template slot-scope="scope">
                      <el-autocomplete
                        v-model="scope.row.reactionDescription"
                        size="small"
                        placeholder="作用机制与结果"
                        clearable
                        :fetch-suggestions="querySearch"
                        @select="handleSelectReaction"
                      >
                        <i class="el-icon-search el-input__icon" slot="suffix"></i>
                      </el-autocomplete>
                    </template>
                  </el-table-column>
                  <el-table-column prop="action" label="审方等级" width="180">
                    <template slot-scope="scope">
                      <el-select size="small" v-model="scope.row.auditLevelId" placeholder="审方等级">
                        <el-option
                          v-for="item in auditlevel"
                          :key="item.id"
                          :label="item.desp"
                          :value="item.id"
                        ></el-option>
                      </el-select>
                    </template>
                  </el-table-column>
                  <el-table-column prop="enable" label="操作" align="center" width="50">
                    <template slot-scope="scope">
                      <i @click="clickDelete(scope.row)" class="el-icon-delete text-red pointer"></i>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="相互作用">
                <el-table :data="interaction" border size="small" style="width: 100%;">
                  <el-table-column prop="medcineId" label="药物B名称">
                    <template slot-scope="scope">{{handleEnumMedcine(scope.row.medcineId)}}</template>
                  </el-table-column>
                  <el-table-column prop="reactionDescription" label="作用机制与结果">
                    <template slot-scope="scope">
                      <el-autocomplete
                        v-model="scope.row.reactionDescription"
                        size="small"
                        placeholder="作用机制与结果"
                        clearable
                        :fetch-suggestions="querySearch"
                        @select="handleSelectReaction"
                      >
                        <i class="el-icon-search el-input__icon" slot="suffix"></i>
                      </el-autocomplete>
                    </template>
                  </el-table-column>
                  <el-table-column prop="enable" label="操作" align="center" width="50">
                    <template slot-scope="scope">
                      <i @click="clickDelete(scope.row)" class="el-icon-delete text-red pointer"></i>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
              <el-tab-pane label="重复用药">
                <el-table :data="repetition" border size="small" style="width: 100%;">
                  <el-table-column prop="medcineId" label="药物B名称">
                    <template slot-scope="scope">{{handleEnumMedcine(scope.row.medcineId)}}</template>
                  </el-table-column>
                  <el-table-column prop="enable" label="操作" align="center" width="50">
                    <template slot-scope="scope">
                      <i @click="clickDelete(scope.row)" class="el-icon-delete text-red pointer"></i>
                    </template>
                  </el-table-column>
                </el-table>
              </el-tab-pane>
            </el-tabs>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import { GetLabelList, GetReaction, GetCompatibilitybyid, DeleteComp, AddComp, Allmedcinefold, SaveComp } from '@/api/dataMana'
import AutoSearch from '@/components/AutoSearch'
import { auditlevel } from '@/global/cfg'
import Drugs from './components/DrugTree'
import Tags from './components/Tags'

export default {
  name: 'combined',
  components: { AutoSearch, Drugs, Tags },
  data() {
    return {
      loading: false,
      tabIndex: 0,
      auditlevel,
      choose: {
        id: '',
        label: ''
      },
      currentKey: null,
      list: [],
      oldList: [],
      compatibility: [], // 配伍禁忌信息
      interaction: [], // 相互作用信息
      repetition: [], // 重复用药信息
      oldCompatibility: [], // 配伍禁忌信息
      oldinteraction: [], // 相互作用信息
      oldRepetition: [], // 重复用药信息
      searchList: [],
      reactionList: [], // 作用机制与结果智能联想输入
      searchKey: '',
      labelList: [] // 获取标签列表
    }
  },
  created() {
    this.getAllMedcine()
    this.getLabelList()
    this.getReactionList(1)
  },
  beforeRouteLeave(to, from, next) {
    let res = true
    if (this.list.length > 0) res = this.oldList === JSON.stringify(this.list)
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
    // 右侧切换
    clickTab2(tab) {
      console.log(tab.index)
      this.tabIndex = Number(tab.index)
      this.getReactionList(this.tabIndex + 1)
    },
    // 左侧切换
    clickTab(tab) {
      console.log(tab.index)
      // this.type = Number(tab.index) + 1
      // this.getAllMedicinelimit()
    },
    // todo
    updateChoose(val) {
      console.log(val)
      let res = true
      if (this.list.length > 0) res = this.oldList === JSON.stringify(this.list)
      if (res) {
        this.choose = val
        this.getList()
        this.currentKey = val
      } else {
        return this.$confirm('当前页面未保存，是否确认离开？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.choose = val
          this.getList()
          this.currentKey = val
        }).catch(() => {
          console.log('cancle')
          this.$nextTick(() => {
            this.$refs.drugTree.$refs.tree.setCurrentKey(this.currentKey)
          })
        })
      }
    },

    // 作用机制与结果智能联想输入
    handleSelectReaction() {

    },
    // 保存
    async handleSave() {
      this.loading = true
      let res
      if (this.tabIndex === 0) {
        res = await SaveComp(JSON.stringify(this.compatibility))
      } else if (this.tabIndex === 1) {
        res = await SaveComp(JSON.stringify(this.interaction))
      } else if (this.tabIndex === 2) {
        res = await SaveComp(JSON.stringify(this.repetition))
      }
      this.loading = false
      if (res.code === 0) {
        this.$message.success('保存成功！')
        this.getList()
      } else {
        this.$message.error(res.msg)
      }
    },
    // 删除
    clickDelete(row) {
      this.$confirm('确定删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(row)
      }).catch(() => { })
    },

    async handleDelete(row) {
      const args = {
        id: row.id ? row.id : '',
        type: row.type ? row.type : '',
        reactionDescription: row.reactionDescription ? row.reactionDescription : '',
        auditLevelId: row.auditLevelId ? row.auditLevelId : '',
        medcineId: row.medcineId ? row.medcineId : '',
        medcinePairId: row.medcinePairId ? row.medcinePairId : '',
        labelId: row.labelId ? row.labelId : '',
        labelPairId: row.labelPairId ? row.labelPairId : '',
        categoryId: row.categoryId ? row.categoryId : '',
        categoryPairId: row.categoryPairId ? row.categoryPairId : ''
      }
      const res = await DeleteComp(JSON.stringify(args))
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.getList()
      } else {
        this.$message.error(res.msg)
      }
    },

    // todo 无接口 增加 配伍
    async handleAdd(val) {
      const obj = {
        medcinePairId: this.choose.id,
        medcineId: val.id,
        reactionDescription: '',
        action: ''
      }
      const res = await AddComp(obj)
      if (res.code === 0) {
        this.getList()
      } else {
        this.$message.error(res.msg)
      }
    },

    // 配伍禁忌
    async getList() {
      const res = await GetCompatibilitybyid({
        id: this.choose.id,
        sign: Number(this.choose.sign)
      })
      if (res.code === 0) {
        this.list = res.result
        this.compatibility = res.result.compatibility // 配伍禁忌信息
        this.interaction = res.result.interaction // 相互作用信息
        this.repetition = res.result.repetition // 重复用药信息
        this.oldCompatibility = JSON.stringify(res.result.compatibility)
        this.oldinteraction = JSON.stringify(res.result.interaction)
        this.oldRepetition = JSON.stringify(res.result.repetition)
      } else {
        this.$message.error(res.msg)
        this.compatibility = [] // 配伍禁忌信息
        this.interaction = [] // 相互作用信息
        this.repetition = [] // 重复用药信息
      }
    },
    // 作用机制与结果智能联想输入
    async getReactionList(type) {
      const res = await GetReaction({ type })
      if (res.code === 0) {
        this.reactionList = res.result
        this.reactionList.forEach((item) => { Object.assign(item, { value: item.reactionDescription }) })
      } else {
        this.$message.error(res.msg)
      }
    },
    // 处理枚举 - 药品名称
    handleEnumMedcine(id) {
      return this.searchList.filter(item => id === item.id)[0].name
    },
    // 获取药品信息
    async getAllMedcine() {
      const res = await Allmedcinefold()
      if (res.code === 0) {
        this.searchList = res.result
        this.searchList.forEach((item) => { Object.assign(item, { value: item.name }) })
      }
    },
    handleSearch() {
      this.getLabelList(this.searchKey)
    },
    // todo 点击 表格
    handleClickRow(row) {

    },
    // 获取标签列表
    async getLabelList() {
      const res = await GetLabelList({ name: this.searchKey })
      if (res.code === 0) {
        this.labelList = res.result
      } else {
        this.$message.error(res.msg)
      }
    },
    // 修改table header的背景色
    tableHeaderName({ row, rowIndex }) {
      return 'table-header'
    },
    querySearch(queryString, cb) {
      const restaurants = this.reactionList
      const results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
      cb(results)
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    }
  }
}
</script>
