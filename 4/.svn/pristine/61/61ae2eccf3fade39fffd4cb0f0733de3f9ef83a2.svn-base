<template>
  <div class="home">
    <el-tabs
      type="card"
      v-model="active"
      @tab-click="handleClick"
    >
      <el-tab-pane name="pending">
        <span slot="label"><i class="el-icon-time"></i> 待处理处方</span>
        <Pending
          v-if="auditcenterId && tabIndex === 0"
          ref="pending"
          :auditCenterId="auditcenterId"
          :platformAuditState="platformAuditState"
          :alltype="alltype"
          :auditState="auditState1"
        />
      </el-tab-pane>
      <el-tab-pane name="history">
        <span slot="label"><i class="el-icon-date"></i> 历史处方</span>
        <History
          v-if="auditcenterId && tabIndex === 1"
          ref="history"
          :auditCenterId="auditcenterId"
          :alltype="alltype"
          :auditState="auditState2"
        />
      </el-tab-pane>
    </el-tabs>
    <el-form
      class="choose"
      label-width="96px"
      :inline="true"
      size="small"
    >
      <el-form-item label="审方中心：">
        <!-- <el-select
          v-model="curAuditCenter.auditcenterId"
          @change="handleChange(curAuditCenter)"
          placeholder="审方中心"
        >
          <el-option
            v-for="item in center"
            :key="item.id"
            :label="item.auditCenterName"
            :value="item.id"
          ></el-option>
        </el-select> -->
        <el-select
          v-model="auditcenterId"
          @change="handleChange"
          placeholder="审方中心"
        >
          <el-option
            v-for="item in center"
            :key="item.id"
            :label="item.auditCenterName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { AllAuditCenter } from '@/api/global'
import { Alltype, Allauditstate } from '@/api/dictionaries'
import History from './history'
import Pending from './pending'

export default {
  name: 'home',
  components: { History, Pending },
  data() {
    return {
      tabIndex: 0,
      alltype: [], // 处方类型
      auditState: [], // 审核状态
      auditState1: [], // 审核状态 - 待处理
      auditState2: [], // 审核状态 - 历史
      curAuditCenter: {}, // 当前审方中心
      auditcenterId: '', // 审方中心id
      platformAuditState: '', // 审方中心状态
      center: [], // 全部审方中心
      active: 'pending'
    }
  },
  created() {
    this.getAllCenter() // 字典 - 全部审方中心
    this.getAllAuditState() // 字典 - 审核状态
    this.getAllType() // 处方类型
  },
  methods: {
    handleClick(tab, event) {
      this.tabIndex = Number(tab.index)
    },
    handleChange(val) {
      console.log(val)
      if (this.tabIndex === 0) {
        this.$refs.pending.getList(val)
      } else if (this.tabIndex === 1) {
        this.$refs.history.getList(val)
      }
    },
    // 字典 - 全部审方中心
    async getAllCenter() {
      const res = await AllAuditCenter()
      if (res.code === 0) {
        this.center = res.result
        this.curAuditCenter = res.result[0]
        this.auditcenterId = res.result[0].id
        this.platformAuditState = res.result[0].platformAuditState
      }
    },
    // 字典 - 审核状态
    async getAllAuditState() {
      const res = await Allauditstate()
      if (res.code === 0) {
        const arr1 = []
        const arr2 = JSON.parse(JSON.stringify(res.result))
        arr2.splice(arr2.findIndex(item => item.id === 8), 1)
        arr2.splice(arr2.findIndex(item => item.id === 9), 1)
        this.auditState2 = [
          {
            id: '',
            stateName: '全部'
          },
          ...arr2
        ]
        const obj1 = res.result.filter(item => item.id === 8)[0]
        const obj2 = res.result.filter(item => item.id === 9)[0]
        arr1.push(obj1, obj2)
        this.auditState1 = [
          {
            id: '',
            stateName: '全部'
          },
          ...arr1
        ]
      }
    },
    // 处方类型
    async getAllType() {
      const res = await Alltype()
      if (res.code === 0) {
        this.alltype = [
          {
            id: '',
            typeName: '全部'
          },
          ...res.result
        ]
      }
    }
  }
}
</script>

<style lang="less" scoped>
.home {
  position: relative;

  .choose {
    position: absolute;
    top: 0;
    right: 0;
  }

  .icon {
    display: inline-block;
    vertical-align: middle;
    width: 20px;
    height: 20px;
    background-size: 100% 100%;
    background-repeat: no-repeat;
    background-position: center;
  }
  .icon-pen {
    background-image: url(../../assets/images/icon-pen.png);
  }

  .icon-his {
    background-image: url(../../assets/images/icon-his.png);
  }
}
</style>

