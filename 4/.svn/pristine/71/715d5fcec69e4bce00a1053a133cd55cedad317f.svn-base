<template>
  <div
    class="navMenu"
    style="width:100%;"
  >
    <template v-for="(navMenu, index) in navMenus">
      <el-menu-item
        v-if="navMenu && navMenu.meta.isSingle"
        :key="index"
        :data="navMenu.children[0]"
        :index="navMenu.children[0].name"
        :route="navMenu.children[0].path"
      >
        <i
          class="icon-menu1"
          :class="navMenu.children[0].icon"
        ></i>
        <span slot="title">{{navMenu.children[0].meta.title}}</span>
      </el-menu-item>
      <el-menu-item
        v-else-if="navMenu && !navMenu.children"
        :key="index"
        :data="navMenu"
        :index="navMenu.name"
        :route="navMenu.path"
        style="overflow-y:hidden; overflow-x: auto"
      >
        <i :class="[{'icon-menu1': navMenu.key==='nav', 'icon-menu2': navMenu.key==='menu', 'icon-menu3': navMenu.key==='level3'}, navMenu.icon]"></i>
        <span slot="title">{{navMenu.meta.title}}</span>
      </el-menu-item>
      <!-- 此菜单下还有子菜单 -->
      <el-submenu
        v-else-if="navMenu && navMenu.children"
        :key="index"
        :data="navMenu"
        :index="navMenu.name"
        :route="navMenu.path"
      >
        <template slot="title">
          <i :class="[{'icon-menu1': navMenu.key==='nav', 'icon-menu2': navMenu.key==='menu', 'icon-menu3': navMenu.key==='level3'}, navMenu.icon]"></i>
          <span slot="title">{{navMenu.meta.title}}</span>
        </template>
        <!-- 递归 -->
        <NavMenu :navMenus="navMenu.children"></NavMenu>
      </el-submenu>
    </template>
  </div>
</template>

<script>
export default {
  name: 'NavMenu',
  props: ['navMenus']
}
</script>
