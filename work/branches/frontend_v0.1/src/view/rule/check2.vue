<template>
  <div style="height: 100%">
    <!-- 检查结果与药品限用 -->
    <el-row
      :gutter="20"
      style="height: 100%"
    >
      <el-col
        :span="9"
        style="height: 100%"
      >
        <el-card
          class="box-card"
          style="height: 100%; overflow-y: auto;"
        >
          <div
            slot="header"
            class="clearfix"
            style="height: 100%;"
          >
            <span class="f4">药品列表</span>
          </div>
          <DrugTree
            ref="drugTree"
            :type="'drugs'"
            @updateChoose="updateChoose"
          />
        </el-card>
      </el-col>
      <el-col
        :span="15"
        style="height: 100%"
      >
        <el-card
          class="box-card"
          style="height: 100%"
        >
          <div
            slot="header"
            class="clearfix"
          >
            <span class="f4">限用设置 </span>
            <template v-if="choose.name">
              <span class="f1 font-bold">【 {{choose.name}} 】</span>
              <el-button
                style="position: absolute; top: 15px; right: 20px;"
                type="primary"
                size="small"
                @click="showModal"
              >添加规则</el-button>
            </template>
          </div>
          <div
            class="f4"
            v-if="!choose.name"
          >请先选择药品</div>
          <el-tabs
            v-if="choose.name"
            :before-leave="beforeLeave"
          >
            <el-tab-pane label="总剂量限用">
              <DosageTab
                :auditlevel="auditlevel"
                :list="dosageList"
                :operatorList="operatorList"
                :unitTypeList="unitTypeList"
                @getList="getAlldosage"
              />
            </el-tab-pane>
            <el-tab-pane label="用药天数限用">
              <DurationTab
                :auditlevel="auditlevel"
                :list="durationList"
                @getList="getAllduration"
              />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog
      title="新建规则"
      :visible.sync="addVisible"
      width="700px"
      :before-close="hideModal"
    >
      <el-form
        ref="form"
        label-width="120px"
        size="small"
        :rules="rules"
        :model="form"
      >
        <el-form-item
          label="规则名称："
          prop="ruleName"
        >
          <el-input
            v-model="form.ruleName"
            style="width: 199px"
            placeholder="输入规则名称"
          ></el-input>
        </el-form-item>
        <el-form-item
          v-if="tabIndex === 0"
          label="总剂量区间："
        >
          <el-input
            v-model="form.dosagePre"
            type="number"
            style="width: 80px"
          ></el-input>
          <el-select
            v-model="form.unitNamePre"
            style="width: 70px"
          >
            <el-option
              v-for="(item, index) in unitTypeList"
              :key="index"
              :label="item.unit"
              :value="item.id"
            ></el-option>
          </el-select>
          <el-select
            v-model="form.relationalOperatorPre"
            style="width: 70px"
          >
            <el-option
              v-for="(item, index) in operatorList"
              :key="index"
              :label="item.operator"
              :value="item.id"
            ></el-option>
          </el-select>
          <span>总剂量</span>
          <el-select
            v-model="form.relationalOperatorPost"
            style="width: 70px"
          >
            <el-option
              v-for="(item, index) in operatorList"
              :key="index"
              :label="item.operator"
              :value="item.id"
            ></el-option>
          </el-select>
          <el-form-item
            prop="doesagePost"
            class="d-inline-block"
            style="margin-bottom: 0;"
          >
            <el-input
              v-model="form.doesagePost"
              type="number"
              style="width: 80px"
            ></el-input>
          </el-form-item>
          <el-select
            v-model="form.unitNamePost"
            style="width: 70px"
          >
            <el-option
              v-for="(item, index) in unitTypeList"
              :key="index"
              :label="item.unit"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          v-if="tabIndex === 1"
          label="最大用药天数："
        >
          <el-form-item
            prop="lowerbound"
            class="d-inline-block"
            style="margin-bottom: 0;"
          >
            <el-input
              v-model="form.lowerbound"
              type="number"
              style="width: 80px"
            ></el-input>
          </el-form-item>
          <span>~</span>
          <el-form-item
            prop="upperbound"
            class="d-inline-block"
            style="margin-bottom: 0;"
          >
            <el-input
              v-model="form.upperbound"
              type="number"
              style="width: 80px"
            ></el-input>
          </el-form-item>
          <span>天</span>
        </el-form-item>
        <el-form-item
          label="审方等级："
          prop="level"
        >
          <el-select
            v-model="form.level"
            placeholder="选择审方等级"
          >
            <el-option
              v-for="(item, index) in auditlevel"
              :key="index"
              :label="item.desp"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button @click="hideModal">取消</el-button>
          <el-button
            type="primary"
            :loading="loading"
            @click="handleAdd"
          >添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { Allduration, Alldosage, Adddosage, Addduration } from '@/api/dataMana'
import { AllOperator, AllUnitType } from '@/api/dictionaries'
import { auditlevel } from '@/global/cfg'
import DrugTree from './components/DrugTree'
import DosageTab from './components/DosageTab'
import DurationTab from './components/DurationTab'

export default {
  name: 'rule',
  components: { DrugTree, DosageTab, DurationTab },
  data() {
    return {
      loading: false,
      addVisible: false,
      choose: {},
      // currentKey: null,
      tabIndex: 0,
      dosageList: [], // 总剂量限用列表
      durationList: [], // 用药天数限用列表
      operatorList: [], // 运算符字典列表
      unitTypeList: [], // 药品单位字典列表
      auditlevel,
      form: {
        medcineId: '',
        ruleName: '',
        level: '',
        dosagePre: '', // 用量--下限
        doesagePost: '', // 用量--上限
        unitNamePre: '', // 单位--下限
        unitNamePost: '', // 单位--上限
        relationalOperatorPre: '', // 关系运算符--下限
        relationalOperatorPost: '', // 关系运算符--上限
        lowerbound: '', // 下限
        upperbound: '' // 上限
      },
      rules: {
        ruleName: [
          { required: true, message: '请输入规则名称', trigger: 'blur' }
        ],
        doesagePost: [
          { required: true, message: '请输入上限用量', trigger: 'blur' }
        ],
        lowerbound: [
          { required: true, message: '请输入最少用药天数', trigger: 'blur' }
        ],
        upperbound: [
          { required: true, message: '请选择最大用药天数', trigger: 'blur' }
        ],
        level: [
          { required: true, message: '请选择审方等级', trigger: 'change' }
        ]
      }
    }
  },
  created() {
    this.getAllOperator()
    this.getAllUnitType()
  },
  methods: {
    showModal() {
      this.addVisible = true
    },
    hideModal() {
      this.$refs.form.resetFields()
      this.addVisible = false
      this.form.dosagePre = ''
      this.form.relationalOperatorPre = this.operatorList[0].id
      this.form.relationalOperatorPost = this.operatorList[0].id
      this.form.unitNamePre = this.unitTypeList[0].id
      this.form.unitNamePost = this.unitTypeList[0].id
    },
    // add
    handleAdd() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.loading = true
          if (this.tabIndex === 0) {
            // 总剂量限用
            this.addDosage()
          } else if (this.tabIndex === 1) {
            // 用药天数限用
            this.addDuration()
          }
        } else {
          console.log('error submit!!')
        }
      })
    },
    beforeLeave(tab) {
      this.tabIndex = Number(tab)
      this.handleData(this.tabIndex)
    },
    updateChoose(val) {
      this.choose = val
      this.handleData(this.tabIndex)
      // this.currentKey = val
    },
    // 判断 调用 限用
    async handleData(tabIndex) {
      if (tabIndex === 0) {
        // 总剂量限用
        await this.getAlldosage()
      } else if (tabIndex === 1) {
        // 用药天数限用
        await this.getAllduration()
      }
    },
    async addDosage() {
      delete this.form.lowerbound
      delete this.form.upperbound
      this.form.medcineId = this.choose.id
      if (this.form.unitNamePre !== this.form.unitNamePost) {
        this.loading = false
        this.$message.error('单位不一致')
      } else {
        const res = await Adddosage(this.form)
        this.loading = false
        if (res.code === 0) {
          this.$message.success(res.msg)
          this.hideModal()
          this.getAlldosage()
        } else {
          this.$message.error(res.msg)
        }
      }
    },
    async addDuration() {
      this.form.medcineId = this.choose.id
      delete this.form.dosagePre
      delete this.form.doesagePost
      delete this.form.unitNamePre
      delete this.form.unitNamePost
      delete this.form.relationalOperatorPre
      delete this.form.relationalOperatorPost
      const res = await Addduration(this.form)
      this.loading = false
      if (res.code === 0) {
        this.$message.success(res.msg)
        this.hideModal()
        this.getAllduration()
      } else {
        this.$message.error(res.msg)
      }
    },
    // get总剂量限用
    async getAlldosage() {
      const res = await Alldosage({
        medcineId: this.choose.id
      })
      if (res.code === 0) {
        this.dosageList = res.result
      } else {
        this.$message.error(res.msg)
        this.dosageList = []
      }
    },
    // get用药天数限用
    async getAllduration() {
      const res = await Allduration({
        medcineId: this.choose.id
      })
      if (res.code === 0) {
        this.durationList = res.result
      } else {
        this.$message.error(res.msg)
        this.durationList = []
      }
    },
    // 获取运算符字典列表
    async getAllOperator() {
      const res = await AllOperator()
      if (res.code === 0) {
        this.operatorList = res.result
        this.form.relationalOperatorPre = this.operatorList[0].id
        this.form.relationalOperatorPost = this.operatorList[0].id
      } else {
        this.$message.error(res.msg)
      }
    },
    // 获取药品单位字典列表
    async getAllUnitType() {
      const res = await AllUnitType()
      if (res.code === 0) {
        this.unitTypeList = res.result
        this.form.unitNamePre = this.unitTypeList[0].id
        this.form.unitNamePost = this.unitTypeList[0].id
      } else {
        this.$message.error(res.msg)
      }
    }
  }
}
</script>
