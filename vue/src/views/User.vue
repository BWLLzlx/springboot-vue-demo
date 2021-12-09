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
        <el-table-column prop="username" label="用户名" sortable />
        <el-table-column prop="nickName" label="昵称" sortable />
        <el-table-column prop="age" label="年龄" sortable />
        <el-table-column prop="sex" label="性别" sortable />
        <el-table-column prop="address" label="地址" />

<!--        加入旁边的操作-->
        <el-table-column fixed="right" prop="operation" label="操作" width="120">
          <template #default="scope">
            <el-button type="text" @click="handleEdit(scope.row)" size="small">编辑</el-button>
            <el-popconfirm title="是否确认删除？" @confirm="handleDelete(scope.row.id)">
              <template #reference>
                <el-button type="text" size="small">删除</el-button>
              </template>
            </el-popconfirm>
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
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickName" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
            <el-radio v-model="form.sex" label="隐私">隐私</el-radio>
          </el-form-item>
          <el-form-item label="地址">
            <el-input type="textarea" v-model="form.address" style="width:80%"></el-input>
          </el-form-item>
        </el-form>
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

import request from "../utils/request";
import {ElMessage} from 'element-plus'

export default {
  name: 'User',
  components: {

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
  created() {
    this.load()
  },
  methods:{
    load() {
      request.get("/api/user", {
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total

      })
    },
    save() {
      if(this.form.id) {//更新
        request.put("/api/user",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            ElMessage('更新成功')
            sessionStorage.setItem("user",JSON.stringify(this.form))
            this.load();
          } else {
            ElMessage(res.msg)
          }
        })
      } else{//新增
        //  操作完之后成功通过request对象把数据发给后台
        request.post("/api/user",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            ElMessage('新增成功')
            sessionStorage.setItem("user",JSON.stringify(this.form))
            this.load();
          } else {
            ElMessage(res.msg)
          }
        })
      }
      this.dialogVisible=false;
      this.load(); //刷新表格数据
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/api/user/" + id).then(res => {
        if(res.code === '0'){
          ElMessage('删除成功')
          this.load();
        } else {
          ElMessage(res.msg)
        }
        this.load(); //刷新表格数据
      })
    },
    handleSizeChange(pageSize) {//改变当前页面个数触发，
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {//改变当前页码触发
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>
