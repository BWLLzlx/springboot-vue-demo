 <template>
   <!--顶栏块-->
   <div style="height: 50px; line-height: 50px; display: flex; border-bottom: dodgerblue solid 1px">
     <!--字-->
     <div>
      <span style="padding: 20px; font-size: 20px; font-weight: bold; color: dodgerblue">
        用户页面
      </span>
     </div>
     <!--空-->
     <div style="flex: 1; "></div>
     <!--下拉菜单-->
     <div style="width: 150px">
       <el-dropdown>
        <span class="el-dropdown-link">
          欢迎您：
          <!--用于动态显示名字-->
          {{ cus.cusName }}
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

export default {
  name: "cusHeader",
  props: ["login cus"],
  data() {
    return {
      cus:{},
    }
  },
  created() {
    let str = sessionStorage.getItem("login cus") || {} //获取到sessionStorage里面的值
    this.cus = JSON.parse(str) //给定义的变量man赋值
  },
  methods: {
    quit() {
      this.$router.push('/')
      sessionStorage.removeItem("login cus")
    },
    check() {
      this.$router.push('/cus/cusPerson')
    },
  }
}
</script>

<style scoped>

</style>