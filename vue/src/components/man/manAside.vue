<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 51px)"
        class="el-menu-vertical-demo"
        :default-active="path"
        router
    >
      <el-menu-item index="/man/manHome">首页</el-menu-item>
      <el-menu-item index="/man/manPerson">个人信息</el-menu-item>
      <el-menu-item index="/man/manCus">用户管理</el-menu-item>
<!--      <el-menu-item index="/man/manBook">书籍管理</el-menu-item>-->
      <el-menu-item index="/man/manCategory">分类管理</el-menu-item>
      <el-menu-item index="/man/manGoods">商品类别</el-menu-item>
      <el-menu-item index="/man/manList">订单管理</el-menu-item>
    </el-menu>

  </div>
</template>

<script>
export default {
  name: "manAside",
  data() {
    return {
      path: this.$route.path
    }
  },
}
</script>

<style scoped>

</style>