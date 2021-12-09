<template>
  <div style="padding: 10px">
    <!--    搜索区域-->
    <div style="margin: 10px 0 ">
      <el-input v-model="search" placeholder="请输入类别名称" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>

    <!--    表格区域-->
    <div>
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column prop="id" label="分类编号" sortable />
        <el-table-column prop="name" label="分类名称" sortable />

      </el-table>
    </div>

    <!--    分页-->
    <div class="block" style="margin: 10px">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>

    </div>
  </div>
</template>

<script>
import request from "../../utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "manCategory",
  components:{

  },
  data() {
    return {
      form: {},
      dialogVisible:false,
      search:'',
      total: 0,
      pageSize: 10,
      currentPage: 1,
      tableData: []
    }
  },
  created() {//页面加载的时候调用这个函数
    this.load()
  },
  methods: {
    load() {//查询，post请求可以传对象，但是get请求需要param
      request.get("/api/man/manCategory",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records //把数据赋值给tableData
        this.total = res.data.total

      })
    },
    handleSizeChange(pageSize) {//改变当前页面个数
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {//改变当前页码
      this.pageNum = pageNum //把修改的数字传给变量
      this.load()//重新加载界面
    }
  }
}
</script>

<style scoped>

</style>