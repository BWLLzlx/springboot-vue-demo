<template>
  <div style="padding: 0 100px;">

    <!--功能区域-->
    <div style="display: flex">
      <!--搜索-->
      <div style="margin: 10px 0 ;width: 300px">
        <el-input v-model="search" placeholder="请输入关键字" style="width: 50%" clearable />
        <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
      </div>
      <!--空-->
      <div style="flex: 1; "></div>
    </div>

    <!--表格区域-->
    <div>
      <!--页表-->
      <el-table :data="tableData" stripe border style="width: 100%">
        <!--普通内容-->
        <el-table-column prop="id" label="编号" sortable />
        <el-table-column prop="name" label="名称" sortable />
        <el-table-column prop="price" label="单价" sortable />
        <el-table-column prop="createTime" label="出版时间" sortable />
<!--        &lt;!&ndash;封面部分&ndash;&gt;-->
<!--        <el-table-column label="封面">-->
<!--          <template #default="scope">-->
<!--            <el-image style="width: 100px; height: 100px" :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>-->
<!--          </template>-->
<!--        </el-table-column>-->
        <!--旁边的操作-->
        <el-table-column fixed="right" prop="operation" label="操作" width="120">
          <template #default="scope">
            <el-button type="text" @click="Buy(scope.row)" size="small">购买</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!--分页区域-->
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

    <!--提示款区域-->
    <div>
      <el-dialog v-model="dialogVisible" title="添加订单" width="500px">
        <el-form :model="form" label-width="120px" :rules="rules">
          <el-form-item label="用户编号">
            <el-input v-model="form.userId" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品编号">
            <el-input v-model="form.id" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品名称">
            <el-input v-model="form.name" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品价格">
            <el-input v-model="form.price" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="购买时间">
            <el-date-picker v-model="form.createTime" value-format="YYYY-MM-DD" type="date" style="width:80%" clearable></el-date-picker>
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
import request from "../../utils/request";
import {ElMessage} from "element-plus";


export default {
  name: "cusGoods",
  data() {
    return {
      form: {},
      dialogVisible:false,
      search:'',
      total: 0,
      pageSize: 10,
      currentPage: 1,
      tableData: [],
      rules: {
        createTime: [
          {required: true, message: '请输入日期', trigger: 'blur'}
        ],
      }
    }
  },
  created() {
    this.load()
  },
  methods:{
    filesUploadSuccess(res) {
      console.log(res)
      this.form.cover = res.data
    },
    load() {
      request.get("/api/man/manGoods", {
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
    handleSizeChange(pageSize) {//改变当前页面个数触发，
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {//改变当前页码触发
      this.currentPage = pageNum
      this.load()
    },
    Buy(row) {
      this.form = JSON.parse(JSON.stringify(row))//避免编辑了之后点取消
      this.dialogVisible=true
      let str = sessionStorage.getItem("login cus") || {} //获取到sessionStorage里面的值
      this.form.userId = JSON.parse(str).id //给定义的变量man赋值
      this.form.goodsId = this.form.id
      this.form.createTime = null
      this.form.status = '未支付'
      this.form.goodsName = this.form.name
    },
    save() {
      request.post("/api/cus/cusGoods",this.form).then(res => {
        console.log(res)
        if(res.code === '0'){
          ElMessage('新增成功')
          sessionStorage.setItem("list",JSON.stringify(this.form))
          this.load();
          this.dialogVisible=false;
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