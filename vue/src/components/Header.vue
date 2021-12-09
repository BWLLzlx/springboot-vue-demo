 <template>
  <!--顶栏块-->
  <div style="height: 50px; line-height: 50px; border-bottom: black solid 1px; display: flex;">
    <!--字-->
    <div>
      <span style="padding: 20px; font-size: 20px; font-weight: bold; color: dodgerblue">
        管理员页面
      </span>
    </div>
    <!--空-->
    <div style="flex: 1; "></div>
    <!--下拉菜单-->
    <div style="width: 200px">
      <el-dropdown>
        <span class="el-dropdown-link">
          管理员：
          {{ user.nickName }}
        </span>

        <!--下拉栏-->
        <template #dropdown>
          <el-dropdown-menu >
            <!--点击的时候调用check函数-->
            <el-dropdown-item @click="check">个人信息</el-dropdown-item>
            <!--点击的时候调用quit函数-->
            <el-dropdown-item @click="quit">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>

import request from "../utils/request";

export default {
  name: "Header",
  props: ["login user"],
  data() {
    return {
      user:{},
    }
  },
  created() {
    let userStr = sessionStorage.getItem("login user") || {}
    this.user = JSON.parse(userStr)
  },
  methods: {
    quit() {
      this.$router.push('/login')
      sessionStorage.removeItem("login user")
    },
    check() {
      let userStr = sessionStorage.getItem("login user") || {}
      this.user = JSON.parse(userStr)
      this.$router.push('/person')
    },
    // toHome() {
    //   this.$router.push('/home')
    // }

  }
}
</script>

<style scoped>

</style>