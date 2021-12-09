<template>
  <el-menu
      style="height: 80px; line-height: 80px; margin-left: 20px; display: flex;"
      :default-active="path"
      router
  >
    <el-menu-item index="/cus/cusHome">首页</el-menu-item>
    <el-menu-item index="/cus/cusPerson">个人信息</el-menu-item>
<!--    <el-menu-item index="/cus/cusRecharge">账户充值</el-menu-item>-->
    <el-menu-item index="/cus/cusGoods">商品</el-menu-item>
    <el-menu-item index="/cus/cusList">购物列表</el-menu-item>
  </el-menu>

</template>

<script>
export default {
  name: "cusAside",
  data() {
    return {
      path: this.$route.path
    }
  },
}
</script>

<style scoped>

</style>