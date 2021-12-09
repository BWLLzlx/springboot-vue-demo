<template>
  <div>
    <div style="text-align: center; margin: auto">
      <span>目前仅支持微信转账，请扫下方二维码支付并注明用户账号</span>
    </div>
    <el-card style="width: 600px; margin: 30px auto;">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item>
          <img style="width: 400px;" src="../../assets/QRcode.png">
        </el-form-item>
      </el-form>
    </el-card>

  </div>

</template>

<script>
export default {
  name: "cusRecharge",
  data() {
    return {
    }
  }
}
</script>

<style scoped>

</style>