<template>
  <div style="text-align: center; margin: 10px">
    <h3 style="color: dodgerblue; font-size: 25px">个人信息</h3>
    <el-card style="width: 300px; margin: 30px auto;">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="id">
          <el-input v-model="form.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="账户名">
          <el-input v-model="form.cusName" disabled></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" show-password></el-input>
        </el-form-item>
<!--        <el-form-item label="账户余额">-->
<!--          <el-input v-model="form.money" disabled></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="地址">
          <el-input v-model="form.address"></el-input>
        </el-form-item>

      </el-form>

      <div style="text-align: center">
        <el-button type="primary" @click="update">保存</el-button>
      </div>

    </el-card>


  </div>

</template>

<script>
import request from "../../utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "cusPerson",
  data() {
    return {
      form: {},
    }
  },
  created() {
    let str = sessionStorage.getItem("login cus") || "{}"
    this.form = JSON.parse(str)
  },
  methods: {
    update() {
      request.put("/api/cus/cusPerson",this.form).then(res => {
        console.log(res)
        if(res.code === '0')  {
          ElMessage('更新成功')
          sessionStorage.setItem("login cus",JSON.stringify(this.form))
        } else {
          ElMessage(res.msg)
        }
      })
    },
  }
}
</script>

<style scoped>

</style>