<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 51px)"
        class="el-menu-vertical-demo"
        :default-active="path"
        router
    >
      <el-menu-item index="/home">首页</el-menu-item>
      <el-menu-item index="/person">个人信息</el-menu-item>
      <el-menu-item index="/user">用户管理</el-menu-item>
      <el-menu-item index="/book">商品管理</el-menu-item>

    </el-menu>

  </div>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      path: this.$route.path
    }
  },
  created() {
  }
}
</script>

<style scoped>

</style>