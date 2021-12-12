<template>
  <div style="padding: 10px">
    <!--功能区域-->
    <div>
      <el-button type="primary" @click="add">新增</el-button>
    </div>

    <!--搜索区域-->
    <div style="margin: 10px 0 ">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>

    <!--表格区域-->
    <div>
      <!--页表-->
      <el-table :data="tableData" stripe border style="width: 100%">
        <!--普通内容-->
        <el-table-column prop="id" label="商品编号" sortable />
        <el-table-column prop="name" label="名称" sortable />
        <el-table-column prop="price" label="单价" sortable />
        <el-table-column prop="createTime" label="出版时间" sortable />
        <!--封面部分-->
        <el-table-column label="封面">
          <template #default="scope">
            <el-image style="width: 100px; height: 100px" :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="categoryId" label="商品类别" sortable />
        旁边的操作
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
      <el-dialog v-model="dialogVisible" title="提示" width="500px">
        <el-form :model="form" label-width="120px">
          <el-form-item label="名称">
            <el-input v-model="form.name" style="width:80%" ></el-input>
          </el-form-item>
          <el-form-item label="价格">
            <el-input v-model="form.price" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="出版时间">
            <el-date-picker v-model="form.createTime" value-format="YYYY-MM-DD" type="date" style="width:80%" clearable></el-date-picker>
          </el-form-item>
          <el-form-item label="封面">
            <el-upload ref="upload" action="http://101.35.255.239:9090/files/upload"  :on-success="filesUploadSuccess">
              <el-button>上传</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="商品类别">
            <el-select v-model="form.categoryId" placeholder="请选择">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
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
  name: "manGoods",
  data() {
    return {
      form: {},
      dialogVisible:false,
      search:'',
      total: 0,
      pageSize: 10,
      currentPage: 1,

      tableData: [],
      options: [
         // label是显示出来的部分，value是真正的值
        { value: 1, label: '饮品' },
        { value: 2, label: '食品' },
        { value: 3, label: '日用品' },
        { value: 4, label: '学习用品' },
        { value: 5, label: '水果蔬菜' },
      ],
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
    add() {
      this.dialogVisible=true;
      this.form = {}; //清除表单数据
      this.$refs['upload'].clearFiles() //清除历史文件列表
    },
    save() {
      if(this.form.id) {//编辑更新
        request.put("/api/man/manGoods",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            ElMessage('更新成功')
            sessionStorage.setItem("goods",JSON.stringify(this.form))
            this.load();
          } else {
            ElMessage(res.msg)
          }
        })
      } else{//新增
        //  操作完之后成功通过request对象把数据发给后台
        request.post("/api/man/manGoods",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            ElMessage('新增成功')
            sessionStorage.setItem("goods",JSON.stringify(this.form))
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
      this.form = JSON.parse(JSON.stringify(row))//避免编辑了之后点取消
      this.dialogVisible = true
      this.$nextTick(() => { //因为点编辑之前那个组件都不存在，那就没法获取ref这个组件，没这个组件还去调用会报错，用nextTick可以解决调用时元素不存在的错误
        this.$refs['upload'].clearFiles()
      })
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/api/man/manGoods/" + id).then(res => {
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

<style scoped>

</style>