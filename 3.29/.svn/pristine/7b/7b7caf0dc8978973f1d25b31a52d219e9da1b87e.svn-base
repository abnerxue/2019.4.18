<template>
  <el-container class="layout">
    <el-header class="header">
      <h1 class="text-white">区域智慧审方系统</h1>
      <div class="user-info">
        <span class="el-dropdown-link f5">欢迎您，{{userName}}</span>
        <img
          class="pointer"
          src="../assets/images/logout.png"
          @click="handleLogout"
          title="退出账号"
          alt=""
        />
      </div>
    </el-header>
    <el-container>
      <el-aside style="background:#1b6999; width: 240px; overflow-y:auto; overflow-x: hidden">
        <div class="f1 text-center text-white">
          <div class="space"></div>
          <div class="space"></div>
          惠邦数据
          <div class="space"></div>
        </div>
        <el-menu
          background-color="#1b6999"
          text-color="#fff"
          active-text-color="#6ebbe5"
          style="width:241px;height: auto;"
          :default-active="$route.name"
          router
        >
          <NavMenu :navMenus="navList"></NavMenu>
        </el-menu>
        <!-- <div class="collapsed"></div> -->
      </el-aside>
      <el-container>
        <el-header class="breadcrumb">
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item>当前位置</el-breadcrumb-item>
            <el-breadcrumb-item>{{$route.matched[0].meta.title}}</el-breadcrumb-item>

            <template v-if="$route.matched[2]">
              <el-breadcrumb-item>{{$route.matched[1].meta.title}}</el-breadcrumb-item>
              <el-breadcrumb-item>
                <span
                  class="font-bold"
                  style="color: #000"
                >{{$route.matched[2].meta.title}}</span>
              </el-breadcrumb-item>
            </template>
            <!-- 工作台 -->
            <template v-if="$route.matched[1].meta.isParent">
              <el-breadcrumb-item
                v-if="this.$route.query.type === 'all'"
                :to="{ path: pathAll }"
              >所有处方查询</el-breadcrumb-item>
              <el-breadcrumb-item
                v-if="this.$route.query.type === 'problem'"
                :to="{ path: pathProblem }"
              >问题处方查询</el-breadcrumb-item>
              <el-breadcrumb-item>
                <span
                  class="font-bold"
                  style="color: #000"
                >{{$route.matched[1].meta.title}}</span>
              </el-breadcrumb-item>
            </template>

            <el-breadcrumb-item v-else>
              <span
                class="font-bold"
                style="color: #000"
              >{{$route.matched[1].meta.title}}</span>
            </el-breadcrumb-item>
          </el-breadcrumb>
        </el-header>
        <el-main style="background:#fff;">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
    <el-footer class="footer f5 text-center">技术支持：苏州惠邦医疗科技有限公司</el-footer>
  </el-container>
</template>
<script>
import NavMenu from '@/components/NavMenu'

export default {
  components: { NavMenu },
  name: '',
  data() {
    return {
      userName: '', // 用户名
      path: '/',
      navList: [],
      pathAll: this.$urls.ALL_PRESCRIPTION,
      pathProblem: this.$urls.PROBLEM_PRESCRIPTION
    }
  },
  mounted() {
    const user = localStorage.getItem('user')
    if (user) {
      this.userName = JSON.parse(user).name || ''
    }
    /**
     * 获取router中的所有项
     * @param{key} 过滤出导航栏所需的项
     */
    const routes = this.$router.options.routes
    const nav = routes && routes.length > 0 && routes.filter(item => item.key === 'nav')
    this.navList = nav && nav.length > 0 && nav.map((item, index) => ({
      ...item,
      children: item.children.filter(menu => menu.key === 'menu')
    }))
  },
  created() {
    const data = {}
    this.userName = data.userName
  },
  methods: {
    // 退出
    handleLogout() {
      localStorage.clear()
      this.$router.push({ path: this.$urls.LOGIN })
    }
  }
}
</script>
<style lang="less" scoped>
@import "../style/self.less";

@headerH: 60px;

.layout {
  width: 100%;
  height: 100%;

  .footer {
    color: #d1d1d1;
    height: 36px !important;
    background: #313131;
    line-height: 36px;
  }
  .header {
    color: #fff;
    background: #087ab8;
    box-shadow: 0 0 3px @black-20;
    // border-bottom: 1px solid #8f8f8f;
    border-bottom: 1px solid #fff;

    h1 {
      font-size: 24px;
      // color: #00496b;
      float: left;
      line-height: @headerH;
    }

    .user-info {
      float: right;
      line-height: @headerH;

      span,
      img {
        display: inline-block;
        vertical-align: middle;
      }
      span {
        margin-right: 5px;
      }
      img {
        width: 20px;
      }
    }
  }

  .breadcrumb {
    height: 40px !important;
    background: #e5e5e5;
    box-sizing: border-box;
    padding: 15px 20px;
    box-shadow: 5px 0 10px @black-80;
  }
}
</style>
