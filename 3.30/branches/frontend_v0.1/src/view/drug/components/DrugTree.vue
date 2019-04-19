<template>
  <div style="height: 100%;">
    <el-card
      class="box-card"
      style="height: 100%; overflow-y: auto;"
      v-if="type === 'paas' || type === 'org'"
    >
      <div
        slot="header"
        class="clearfix"
      >
        <span class="f4">药品列表</span>
      </div>
      <div>
        <el-input
          size="small"
          placeholder="输入关键字进行过滤"
          v-model="filterText"
          clearable
        >
        </el-input>
        <div class="space"></div>
        <el-tree
          ref="tree"
          :data="list"
          node-key="id"
          :props="defaultProps"
          highlight-current
          auto-expand-parent
          :filter-node-method="filterNode"
          @node-click="handleNodeClick"
          :default-expanded-keys="[0]"
        ></el-tree>
      </div>
    </el-card>
  </div>
</template>

<script>
import { Allcategory } from '@/api/dictionaries'
import { Allmedcinefold } from '@/api/dataMana'

export default {
  props: ['type'],
  name: '',
  data() {
    return {
      filterText: '',
      allcategory: [], // 药品分类
      allmedcine: [], // 药品信息
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      list: [{
        id: '01',
        name: "按药理作用分类",
        children: []
      }]
    }
  },
  async created() {
    await this.getAllCategory();
    if (this.type != 'paas' && this.type != 'org') {
      await this.getAllMedcine();
    }
    await this.formatList(this.list, '01');
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    handleNodeClick(value) {
      this.$emit('updateChoose', value)     
    },
    // 获取药品信息
    async getAllMedcine() {
      const res = await Allmedcinefold()
      if (res.code === 0) {
        this.allmedcine = res.result;
        await this.formatMedcine();
      }
    },
    // 获取药品分类
    async getAllCategory() {
      const res = await Allcategory();
      if (res.code === 0) {
        this.allcategory = res.result;
        this.allcategory.forEach((item) => {
          item.children = [];
        })
      }
    },

    formatMedcine() {
      this.allmedcine.forEach((medcine) => {
        this.allcategory.forEach((category) => {
          if (medcine.categoryId == category.id) {
            category.children.push(medcine);
          }
        })
      })
    },

    formatList(arr, mypid) {
      this.allcategory.forEach((category) => {
          if (arr && arr.length > 0) {
            arr.forEach((item) => {
              if (item.id == mypid && mypid == category.pid) {
                item.children.push(category);
                this.formatList(item.children, category.id);
              }
            })
          } else {
            return false;
          }
      })
    }
  }
}
</script>
