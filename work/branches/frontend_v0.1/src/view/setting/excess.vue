<template>
  <!-- 超日用量时间设置 -->
  <form
    :model="data"
    class="system"
  >
    <div class="line">
      <h6 class="f5">{{data.name0}}：</h6>
      <div class="line-cont">
        <input
          class="f5 txt-input width-lar"
          type="text"
          placeholder=""
          v-model="data.value0"
        >
        <span class="f5">天</span>
      </div>
    </div>
    <div class="line">
      <h6 class="f5">{{data.name1}}：</h6>
      <div class="line-cont">
        <input
          class="f5 txt-input width-lar"
          type="text"
          placeholder=""
          v-model="data.value1"
        >
        <span class="f5">天</span>
      </div>
    </div>
    <div class="line">
      <h6 class="f5">{{data.name2}}：</h6>
      <div class="line-cont">
        <input
          class="f5 txt-input width-lar"
          type="text"
          placeholder=""
          v-model="data.value2"
        >
        <span class="f5">天</span>
      </div>
    </div>
    <div class="line">
      <h6 class="f5">{{data.name3}}：</h6>
      <div class="line-cont">
        <input
          class="f5 txt-input width-lar"
          type="text"
          placeholder=""
          v-model="data.value3"
        >
        <span class="f5">天</span>
      </div>
    </div>
    <div class="line">
      <h6 class="f1"></h6>
      <div class="line-cont">
        <el-button
          type="primary"
          size="small"
          @click="handleSubmit"
          :loading="loading"
        >{{buttonText}}</el-button>
      </div>
    </div>
  </form>
</template>
<script>
import { UpdateRangeConfig, SelectSetupTime } from '@/api/setting'
import { Allauditlevel } from '@/api/dictionaries'

export default {
  name: 'excess',
  data() {
    return {
      data: {},
      buttonText: '保 存',
      loading: false
    }
  },
  async created() {
    await this.getAllAuditlevel()
    await this.getAllConfig()
  },
  methods: {
    // 保存
    async handleSubmit() {
      var re = /^[1-9]+[0-9]*]*$/;
      if (!re.test(this.data.value0)) {
        this.$message.error('抗生素最大处方用药时间必须为正整数');
        return false;
      } else if (!re.test(this.data.value1)) {
        this.$message.error('急诊处方用药天数必须为正整数');
        return false;
      } else if (!re.test(this.data.value2)) {
        this.$message.error('普通疾病门诊用药天数必须为正整数');
        return false;
      } else if (!re.test(this.data.value3)) {
        this.$message.error('慢性疾病门诊用药天数必须为正整数');
        return false;
      }
      this.loading = true
      this.buttonText = '正在保存...'
      this.data.value0 = String(this.data.value0)
      this.data.value1 = String(this.data.value1)
      this.data.value2 = String(this.data.value2)
      this.data.value3 = String(this.data.value3)
      const list = this.data
      const newlist = []
      for (let i = 0; i < Object.keys(list).length / 2; i++) {
        newlist.push({
          name: list[`name${i}`],
          values: list[`value${i}`]
        })
      }
      const res = await UpdateRangeConfig(JSON.stringify(newlist))
      if (res.code === 0) {
        this.loading = false
        this.buttonText = '保 存'
        this.$message.success('保存成功！')
      } else {
        this.loading = false
        this.buttonText = '保 存'
        this.$message.error(res.msg)
      }
    },
    // 获取全部系统设置
    async getAllConfig() {
      const res = await SelectSetupTime()
      if (res.code === 0) {
        this.data = res.result
      } else {
        this.$message.error(res.msg)
      }
    },
    // 审核等级
    async getAllAuditlevel() {
      const res = await Allauditlevel()
      if (res.code === 0) {
        this.auditlevel = res.result
      } else {
        this.$message.error(res.msg)
      }
    }
  }
}
</script>

<style lang="less" scoped>
@import "../../style/self.less";

.system {
  .line {
    padding: 6px 5%;

    * {
      display: inline-block;
      vertical-align: middle;
    }

    h6 {
      width: 200px;
      text-align: right;
    }
  }
}

.width-lar {
  width: 160px;
}
.width-mid {
  width: 80px;
}
.width-sm {
  width: 50px;
}
</style>
