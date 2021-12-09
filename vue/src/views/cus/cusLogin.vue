<template>
  <div style="width: 100%; height: 100vh; background-color: darkslateblue; overflow: auto">
    <div style="width: 300px; margin: 100px auto">
      <div style="color: #ccc; font-size: 50px; font-weight: bold; text-align: center; margin: 30px">用户登录</div>
      <el-form :rules="rules" ref="form" :model="form" size="normal" style="margin: 5px auto">
        <el-form-item prop="cusName">
          <el-input  v-model="form.cusName" placeholder="账户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" placeholder="密码" show-password></el-input>
        </el-form-item>
      </el-form>
      <el-button style="width: 100%; margin: 5px auto" size="normal" type="primary" @click="login">登录</el-button>
      <el-button style="width: 100%; margin: 5px auto" size="normal" type="primary" @click="register">注册</el-button>
    </div>
  </div>
</template>

<script>
import request from "../../utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "cusLogin",
  data() {
    return {
      form: {},
      rules: {
        cusName: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    login() {
      request.post("/api/cus/cusLogin",this.form).then(res => {
        if (res.code === '0') {
          ElMessage('登录成功')
          this.$router.push("/cus/cusHome") //登陆成功跳转到home
          sessionStorage.setItem("login cus", JSON.stringify(res.data)) //缓存用户信息到sessionStorage中，可以在其他时候被getItem获取
        } else {
          ElMessage(res.msg)
        }
      })
    },

    register() {
      this.$router.push("/cus/cusRegister")
    },
  }
}
</script>

<style scoped>

</style>