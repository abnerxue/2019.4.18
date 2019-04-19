<template>
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
    ></el-tree>
  </div>
</template>
<script>
import { Alltype, Allclassify2, Allclassify1 } from '@/api/dictionaries'
import { Allmedcinefold } from '@/api/dataMana'

export default {
  name: '',
  data() {
    return {
      filterText: '',
      alltype: [], // 处方类型
      allclassify2: [], // 小分类
      allclassify1: [], // 大分类
      list: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  async created() {
    await this.getAllType()
    await this.getAllClassify2()
    await this.getAllClassify1()
    await this.getAllMedcine()
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 获取药品信息
    async getAllMedcine() {
      const res = await Allmedcinefold()
      if (res.code === 0) {
        this.list = res.result
        this.handleList()
        this.list = this.formatList(res.result)
      }
    },
    // 匹配字典，修改数组
    handleList() {
      if (this.list && this.list.length > 0) {
        this.list.forEach((item) => {
          Object.assign(item, {
            type: this.alltype.filter(step => Number(item.type) === step.id)[0].typeName,
            category: this.allclassify1.filter(step => Number(item.category) === step.id)[0].categoryName,
            subcategory: this.allclassify2.filter(step => Number(item.subcategory) === step.id)[0].subcategoryName
          })
        })
      }
    },
    formatList(list = []) {
      let typeId = 0
      // 处方类型
      const typeArr = [...new Set(list.map(item => item.type))].map(item => ({
        label: item,
        id: 't' + ++typeId
      }))
      // 大分类
      list.forEach((item) => {
        let parent = typeArr.find(x => x.label === item.type),
          children = parent.children
        if (children) {
          !children.find(x => x.label === item.category) && children.push({
            id: 'c' + ++typeId,
            label: item.category
          })
        } else {
          parent.children = [{
            id: ++typeId,
            label: item.category
          }]
        }
      })
      // 小分类
      list.forEach((item) => {
        let parent = typeArr
          .find(x => x.label === item.type).children
          .find(y => y.label === item.category),
          children = parent.children
        if (children) {
          !children.find(x => x.label === item.subcategory) && children.push({
            id: 's' + ++typeId,
            label: item.subcategory
          })
        } else {
          parent.children = [{
            id: ++typeId,
            label: item.subcategory
          }]
        }
      })
      // 药名
      list.forEach((item) => {
        let nameObj = typeArr
          .find(x => x.label === item.type).children
          .find(y => y.label === item.category).children
          .find(j => j.label === item.subcategory),
          obj = {
            id: item.id,
            label: item.name
          }
        if (nameObj.children) {
          nameObj.children.push(obj)
        } else {
          nameObj.children = [obj]
        }
      })
      return typeArr
    },

    handleNodeClick(value) {
      if (!value.children) {
        this.$emit('updateChoose', value)
      }
    },
    // 处方类型
    async getAllType() {
      const res = await Alltype()
      if (res.code === 0) {
        this.alltype = res.result
      }
    },
    // 小分类
    async getAllClassify2() {
      const res = await Allclassify2()
      if (res.code === 0) {
        this.allclassify2 = res.result
      }
    },
    // 大分类
    async getAllClassify1() {
      const res = await Allclassify1()
      if (res.code === 0) {
        this.allclassify1 = res.result
      }
    }
  }
}
</script>
