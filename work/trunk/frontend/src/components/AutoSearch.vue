<template>
  <div>
    <el-autocomplete
      v-model="selectValue"
      size="small"
      :placeholder="placeHolder"
      clearable
      :fetch-suggestions="querySearch"
      @select="click"
    >
      <i
        class="el-icon-search el-input__icon"
        slot="suffix"
      ></i>
    </el-autocomplete>
  </div>
</template>

<script>
export default {
  props: ['list', 'placeHolder'],
  data() {
    return {
      selectValue: ''
    }
  },
  methods: {
    click(item) {
      this.$emit('handleSelect', item)
    },
    // clear(item) {
    //   this.$emit('handleClear', item)
    // },
    querySearch(queryString, cb) {
      const restaurants = this.list
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
