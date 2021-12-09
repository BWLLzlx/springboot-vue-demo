<template>
  <div style="width: 100%; height: 100vh; background-color: darkslateblue; overflow: auto">
    <div style="width: 300px; margin: 100px auto">
      <div style="color: #ccc; font-size: 50px; font-weight: bold; text-align: center; margin: 30px">注册</div>
      <el-form ref="form" :model="form" size="normal" :rules="rules">
        <el-form-item prop="cusName">
          <el-input  v-model="form.cusName" placeholder="账户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" placeholder="密码" show-password></el-input>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input v-model="form.confirm" placeholder="确认密码" show-password></el-input>
        </el-form-item>
        <el-form-item prop="address">
          <el-input v-model="form.address" placeholder="地址" show-password></el-input>
        </el-form-item>
      </el-form>
      <el-button style="width: 100%; " size="normal" type="primary" @click="register">注册</el-button>
    </div>
  </div>
</template>

<script>

import {ElMessage} from "element-plus";
import request from "../../utils/request";

export default {
  name: "cusRegister",
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
        confirm: [
          {required: true, message: '请确认密码', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '请输入地址', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    register() {
      if(this.form.password !== this.form.confirm) {
        ElMessage('两次密码输入不一致')
        return
      }
      this.$refs['form'].validate((valid) => {
        if(valid) {
          request.post("/api/cus/cusRegister", this.form).then(res => {
            if(res.code === '0'){
              ElMessage('注册成功')
              this.$router.push("/cus/cusLogin")
            } else {
              ElMessage(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>