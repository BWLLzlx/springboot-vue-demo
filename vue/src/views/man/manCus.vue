<template>
  <div style="padding: 10px">

    <!--    搜索区域-->
    <div style="margin: 10px 0 ">
      <el-input v-model="search" placeholder="请输入用户名" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>

    <!--    表格区域-->
    <div>
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column prop="id" label="ID" sortable />
        <el-table-column prop="cusName" label="用户名" sortable />
        <el-table-column prop="money" label="余额" sortable />
        <el-table-column prop="address" label="地址" />

        <!--        加入旁边的操作-->
        <el-table-column fixed="right" prop="operation" label="操作" width="80">
          <template #default="scope">
            <el-button type="text" @click="handleEdit(scope.row)" size="small">编辑</el-button>
          </template>
        </el-table-column>

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

      <!--      提示框-->
      <el-dialog v-model="dialogVisible" title="提示" width="500px">
        <el-form :model="form" label-width="120px">
          <el-form-item label="ID">
            <el-input v-model="form.id" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="用户名">
            <el-input v-model="form.cusName" style="width:80%" disabled></el-input>
          </el-form-item>
<!--          <el-form-item label="余额">-->
<!--            <el-input v-model="form.money" style="width:80%"></el-input>-->
<!--          </el-form-item>-->
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width:80%" disabled></el-input>
          </el-form-item>
        </el-form>
<!--        确认操作,暂时不用,暂时用于查看详情-->
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">确定</el-button>
          </span>
        </template>
      </el-dialog>

    </div>
  </div>
</template>

<script>
import request from "../../utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "manCus",
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
      request.get("/api/man/manCus",{
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
    // 暂时不用于修改
    save() {//save是操作弹窗的，有可能是新增的时候操作，有可能是更新的时候操作
      if (this.form.id) {//有id，说明是更新
        request.put("/api/man/manCus", this.form).then(res => {
          console.log(res)//输出结果
          if (res.code === '0') {
            ElMessage('更新成功')
            this.dialogVisible = false
            this.load() //刷新表格数据
          } else {
            ElMessage(res.msg)
          }
        })
      }
    },
    handleEdit(row) {
      //弹窗的表单和form绑定，对form赋值即可
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true;
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