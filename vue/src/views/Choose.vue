<template>
  <div style="width: 100%; height: 100vh; background-color: darkslateblue; overflow: auto">
    <div style="width: 300px; margin: 100px auto">
      <div style="color: #ccc; font-size: 50px; font-weight: bold; text-align: center; margin: 30px">请选择</div>
      <el-button style="width: 100%; margin: 5px auto" size="normal" type="primary" @click="toMan">后台登录</el-button>
      <el-button style="width: 100%; margin: 5px auto" size="normal" type="primary" @click="toCus">用户登录</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Choose",
  methods: {
    toMan() {
      this.$router.push("/man")
    },
    toCus() {
      this.$router.push("/cus")
    },
  }
}
</script>

<style scoped>

</style>