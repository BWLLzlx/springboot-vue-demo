<template>
  <div style="height: 50px; line-height: 50px; border-bottom: black solid 1px; display: flex;">
    <div>
      <span style="padding: 20px; font-size: 20px; font-weight: bold; color: dodgerblue">
        管理员页面
      </span>
    </div>
    <div style="flex: 1; "></div>
    <div style="width: 200px">
      <el-dropdown>
        <span class="el-dropdown-link">
          欢迎您：
          <!--用于动态显示名字-->
          {{ man.manName }}
        </span>

        <template #dropdown>
          <el-dropdown-menu >
            <el-dropdown-item @click="check">个人信息</el-dropdown-item>
            <el-dropdown-item @click="quit">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "manHeader",
  props: ["login man"],
  data() {
    return {
      man:{},
    }
  },
  created() {
    let manStr = sessionStorage.getItem("login man") || {} //获取到sessionStorage里面的值
    this.man = JSON.parse(manStr) //给定义的变量man赋值
  },
  methods: {
    quit() {
      this.$router.push('/')
      sessionStorage.removeItem("login man")
    },
    check() {
      this.$router.push('/man/manPerson')
    },

  }
}
</script>

<style scoped>

</style>