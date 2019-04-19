<template>
  <div style="height: 100%">
    <!-- 抗感染药物禁忌症 -->
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
                @click=""
              >保存</el-button>
            </template>
          </div>
          <div
            class="f4"
            v-if="!choose.name"
          >请先选择药品</div>
          <el-tabs
            v-if="choose.name"
          >
            <el-tab-pane label="病生理">
            </el-tab-pane>
            <el-tab-pane label="人群">
            </el-tab-pane>
            <el-tab-pane label="年龄段">
            </el-tab-pane>
            <el-tab-pane label="孕周">
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import DrugTree from './components/DrugTree'
export default {
  name: '',
  components: { DrugTree },
  data() {
    return {
      choose: {},
    }
  },
  methods: {
    updateChoose(val) {
      console.log(val);
      this.choose = val
    //   this.handleData(this.tabIndex)
      // this.currentKey = val
    },
  }
}
</script>
