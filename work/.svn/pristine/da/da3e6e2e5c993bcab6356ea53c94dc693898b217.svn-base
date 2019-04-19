<template>
  <div>
    <el-form
      :inline="true"
      :model="form"
      label-width="100px"
      size="small"
    >
      <el-form-item label="单位名称：">
        <el-select
          v-model="form.standardSource"
          placeholder="单位名称"
        >
          <el-option
            v-for="item in allhospital"
            :key="item.id"
            :label="item.unitName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="中西药：">
        <el-select
          v-model="form.type"
          placeholder="中西药"
          @change="getCategory"
        >
          <el-option
            v-for="item in alltype"
            :key="item.id"
            :label="item.typeName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="大分类：">
        <el-select
          v-model="form.category"
          placeholder="大分类"
          @change="getSubCategory"
        >
          <el-option
            v-for="item in allclassify1"
            :key="item.id"
            :label="item.categoryName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="小分类：">
        <el-select
          v-model="form.subcategory"
          placeholder="小分类"
        >
          <el-option
            v-for="item in allclassify2"
            :key="item.id"
            :label="item.subcategoryName"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          @click="handleSearch"
        >查询</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="20">
      <el-col :span="16">
        <AutoSearch
          :placeHolder="'输入药品名称'"
          :list="medcineList"
          @handleSelect="handleSearch"
        />
      </el-col>
      <el-col
        :span="8"
        class="text-right"
      >
        <el-button
          size="small"
          type="primary"
          @click="handleUpdate"
        >同步数据</el-button>
        <el-button
          size="small"
          type="primary"
          :loading="loading"
          @click="handleSave"
          v-if="type == 'org'"
        >保存</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// todo 原型图修改，查询条件改变，添加树结构

import { Alltype, Allclassify2, Allclassify1, Allhospital } from '@/api/dictionaries'
import { Allmedcinefold, SaveMedcinehos } from '@/api/dataMana'
import AutoSearch from '@/components/AutoSearch'

export default {
  props: ['type'],
  components: { AutoSearch },
  data() {
    return {
      typeId: '',
      loading: false,
      medcineList: [],
      alltype: [], // 处方类型
      allclassify2: [], // 小分类
      allclassify1: [], // 大分类
      allhospital: [], // 医院
      form: {
        standardSource: '',
        type: '',
        category: '',
        subcategory: ''
      }
    }
  },

  async created() {
    this.getAllMedcine()
    await this.getAllType() // 处方类型
    await this.getAllClassify2() // 小分类
    await this.getAllClassify1() // 大分类
    await this.getAllHospital() // 医院
  },
  methods: {
    // 选择 中西药 后 删选 大小分类
    getCategory(value) {
      this.typeId = value
      this.getAllClassify1(value)
      this.getAllClassify2({ typeId: value })
    },
    // 选择 大小分类 后 删选 小分类
    getSubCategory(value) {
      this.getAllClassify2({ typeId: this.typeId, classify1Id: value })
    },
    handleUpdate() {
      console.log('同步数据')
      this.$message.error('等待接口')
    },
    async handleSearch(val) {
      if (val.name && val.id) {
        this.form.name = val.name
      } else {
        this.form.name = ''
      }
      this.$parent.page = {
        pageSize: 10, // 每页显示10条数据
        pageNo: 1, // 当前页码
        totalCount: '',
        totalPage: ''
      }
      await this.$parent.getList(this.form)
    },
    // save
    async handleSave() {
      this.loading = true
      if (this.type === 'org') {
        const newList = JSON.stringify(this.$parent.arr)
        const res = await SaveMedcinehos(newList)
        this.loading = false
        if (res.code === 0) {
          this.$message.success('保存成功！')
          this.$parent.getList()
        } else {
          this.$message.error(res.msg)
        }
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
    },
    async getAllClassify2(arg) {
      let args = {}
      if (arg) {
        args = { type: arg.typeId, classify1: arg.classify1Id }
      }
      const res = await Allclassify2(args)
      if (res.code === 0) {
        this.allclassify2 = [
          {
            id: '',
            subcategoryName: '全部'
          },
          ...res.result
        ]
      }
    },
    async getAllClassify1(id) {
      const res = await Allclassify1({ type: id })
      if (res.code === 0) {
        this.allclassify1 = [
          {
            id: '',
            categoryName: '全部'
          },
          ...res.result
        ]
      }
    },
    async getAllHospital() {
      const res = await Allhospital()
      if (res.code === 0) {
        this.allhospital = [
          {
            id: '',
            unitName: '全部'
          },
          ...res.result
        ]
      }
    },
    // 获取药品信息
    async getAllMedcine() {
      const res = await Allmedcinefold()
      if (res.code === 0) {
        this.medcineList = res.result
        this.medcineList.forEach((item) => { Object.assign(item, { value: item.name }) })
      }
    }
  }
}
</script>
