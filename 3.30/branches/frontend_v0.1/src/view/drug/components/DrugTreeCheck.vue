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
      style="height: 500px; overflow-y: auto;"
      ref="tree"
      :data="list"
      node-key="id"
      :props="defaultProps"
      highlight-current
      auto-expand-parent
      show-checkbox
      :filter-node-method="filterNode"
      @node-click="handleNodeClick"
      :default-expanded-keys="defaultExpandKey"
      @check="getCheckedKeys"
    ></el-tree>
  </div>
</template>

<script>
import { Allcategory } from '@/api/dictionaries'
import { Allmedcinefold } from '@/api/dataMana'

export default {
  props: ['medicinesKey'],
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
        id: 't01',
        name: "按药理作用分类",
        children: []
      }],
      // 树折叠
      defaultExpand: false,
      // 树默认展开节点
      defaultExpandKey: ['t01'],
      // 修改选中节点，只获取药品id
      checkArr: []
    }
  },
  async created() {
    await this.getAllCategory();
    await this.getAllMedcine();
    await this.formatList(this.list, 't01');
    await this.setCheckedKeys();
  },
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val)
    },
    medicinesKey() {
      this.setCheckedKeys();
      this.toFold();
    }
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    getCheckedKeys() {
      this.checkArr = [];
      let value = this.$refs.tree.getCheckedKeys();
      if (value.length > 0) {
        value.map((item, index) => {
          if (typeof(item) === 'number') {
            this.checkArr.push(item);
          }
        })
      }
      this.$emit('getCheckKey', this.checkArr); 
    },
    // 通过key设置勾选
    setCheckedKeys() {
      this.$refs.tree.setCheckedKeys(this.medicinesKey);
    },
    // 折叠树
    toFold() {
      this.defaultExpand = false;
      for (let i in this.$refs.tree.store._getAllNodes()) {
        if (this.$refs.tree.store._getAllNodes()[i].data.id === 't01') {
          this.$refs.tree.store._getAllNodes()[i].expanded = true;
        } else {
          this.$refs.tree.store._getAllNodes()[i].expanded = this.defaultExpand;
        }
      }
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
          item.id = 't' + item.id;
          item.pid = 't' + item.pid;
          item.children = [];
        })
      }
    },

    formatMedcine() {
      this.allmedcine.forEach((medcine) => {
        this.allcategory.forEach((category) => {
          if ('t' + medcine.categoryId == category.id) {
            medcine.categoryId = 't' + medcine.categoryId;
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

