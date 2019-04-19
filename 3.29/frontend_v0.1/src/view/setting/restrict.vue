<template>
  <div>
    检查限用
  </div>
  <!-- 检查限用 -->
  <!-- <form
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
        <span class="f5">秒</span>
      </div>
    </div>
    <div class="line">
      <h6 class="f5">{{data.name1}}：</h6>
      <div class="line-cont">
        <select
          class="select width-lar pointer"
          v-model="data.value1"
          @change="getSelectedValue1"
        >
          <option
            :value="item.id"
            v-for="item in auditlevel"
            :key="item.id"
          >{{item.desp}}</option>
        </select>
      </div>
    </div>
    <div class="line">
      <h6 class="f5">{{data.name2}}：</h6>
      <div class="line-cont">
        <select
          class="select width-lar pointer"
          v-model="data.value2"
          @change="getSelected"
        >
          <option
            :value="item.id"
            v-for="item in selectList"
            :key="item.id"
          >{{item.cont}}</option>
        </select>
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
  </form> -->
</template>
<script>
import { SelectAllConfig, UpdateConfig } from '@/api/setting'
import { Allauditlevel } from '@/api/dictionaries'

export default {
  name: 'restrict',
  data() {
    return {
      data: {},
      auditlevel: [],
      buttonText: '保 存',
      loading: false,
      selectId: 2,
      selectList: [
        {
          id: 0,
          cont: '手动'
        },
        {
          id: 1,
          cont: '自动'
        }
      ]
    }
  },
  async created() {
    await this.getAllAuditlevel()
    await this.getAllConfig()
  },
  methods: {
    // 保存
    async handleSubmit() {
      this.loading = true
      this.buttonText = '正在保存...'
      this.data.value0 = String(this.data.value0)
      this.data.value1 = String(this.data.value1)
      this.data.value2 = String(this.data.value2)
      const list = this.data
      const newlist = []
      for (let i = 0; i < Object.keys(list).length / 2; i++) {
        newlist.push({
          name: list[`name${i}`],
          values: list[`value${i}`]
        })
      }
      const res = await UpdateConfig(JSON.stringify(newlist))
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
    // 处方含未同步药物
    getSelectedValue1() {
      console.log('value1: ', this.data.value1)
    },

    // 药品信息同步 0手动; 1自动
    getSelected() {
      console.log('value3: ', this.data.value3)
    },
    // 获取全部系统设置
    async getAllConfig() {
      const res = await SelectAllConfig()
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
