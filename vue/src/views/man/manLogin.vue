<template>
  <div style="width: 100%; height: 100vh; background-color: darkslateblue; overflow: auto">
    <div style="width: 300px; margin: 100px auto">
      <div style="color: #ccc; font-size: 50px; font-weight: bold; text-align: center; margin: 30px">后台登录</div>
      <el-form :rules="rules" ref="form" :model="form" size="normal" style="margin: 5px auto">
        <el-form-item prop="manName">
          <el-input  v-model="form.manName" placeholder="账户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" placeholder="密码" show-password></el-input>
        </el-form-item>
      </el-form>
      <el-button style="width: 100%; margin: 5px auto" size="normal" type="primary" @click="login">登录</el-button>
<!--      <el-button style="width: 100%; margin: 5px auto" size="normal" type="primary" @click="register">注册</el-button>-->
    </div>
  </div>
</template>

<script>
import request from "../../utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "manLogin",
  data() {
    return {
      form: {},
      rules: {
        manName: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    //登录，请求后台，把表单传到后台，后台根据数据的用户名和密码进行验证
    login() {
      request.post("/api/man/manLogin",this.form).then(res => {
        if (res.code === '0') {
          ElMessage('登录成功')
          this.$router.push("/man/manHome") //登陆成功跳转到home
          sessionStorage.setItem("login man", JSON.stringify(res.data)) //缓存用户信息到sessionStorage中，可以在其他时候被getItem获取
        } else {
          ElMessage(res.msg)
        }
      })
    },

    register() {
      this.$router.push("/man/manRegister")
    },
  }
}
</script>

<style scoped>

</style>