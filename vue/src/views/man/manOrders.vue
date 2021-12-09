<template>
  <div style="padding: 10px">

    <!--    搜索区域-->
    <div style="margin: 10px 0 ">
      <el-input v-model="search" placeholder="请输入订单号" style="width: 20%" clearable />
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>

    <!--    表格区域-->
    <div>
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column prop="id" label="ID" sortable />
        <el-table-column prop="goodsId" label="商品ID" />
        <el-table-column prop="price" label="单价" sortable />
        <el-table-column prop="number" label="数目" sortable />
        <el-table-column prop="totalPrice" label="总价" sortable />
        <el-table-column prop="createTime" label="创建时间" sortable />
        <el-table-column prop="cusId" label="顾客ID" />
        <el-table-column prop="status" label="发货状态" />


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
          <el-form-item label="ID">
            <el-input v-model="form.id" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品ID">
            <el-input v-model="form.goodsId" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="单价">
            <el-input v-model="form.price" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="数目">
            <el-input v-model="form.number" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="总价">
            <el-input v-model="form.totalPrice" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="创建时间">
            <el-date-picker v-model="form.createTime" value-format="YYYY-MM-DD" type="date" style="width:80%" clearable></el-date-picker>
          </el-form-item>
          <el-form-item label="顾客ID">
            <el-input v-model="form.cusId" style="width:80%" disabled></el-input>
          </el-form-item>
          <el-form-item label="发货状态">
            <el-radio v-model="form.state" label="已发货">已发货</el-radio>
            <el-radio v-model="form.state" label="未发货">未发货</el-radio>
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
  name: "manOrders",
  data() {
    return {
      form: {},
      dialogVisible:false,
      search:'',
      total: 0,
      pageSize: 10,
      currentPage: 1,
      tableData: [

      ]
    }
  },
  created() {//页面加载的时候调用这个函数
    this.load()
  },
  methods: {
    load() {
      request.get("/api/man/manOrders", {
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
        request.put("/api/man/manOrders",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            ElMessage('更新成功')
            sessionStorage.setItem("orders",JSON.stringify(this.form))
            this.load();
          } else {
            ElMessage(res.msg)
          }
        })
      }
      else{//新增
        //  操作完之后成功通过request对象把数据发给后台
        request.post("/api/man/manOrders",this.form).then(res => {
          console.log(res)
          if(res.code === '0'){
            ElMessage('新增成功')
            sessionStorage.setItem("orders",JSON.stringify(this.form))
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
      this.$nextTick(() => { //因为点编辑之前那个组件都不存在，那就没法获取ref这个组件，没这个组件还去调用会报错，用nextTick可以解决调用时元素不存在的错误
        this.$refs['upload'].clearFiles()
      })
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/api/man/manOrders/" + id).then(res => {
        if(res.code === '0'){
          ElMessage('删除成功')
          this.load();
        } else {
          ElMessage(res.msg)
        }
        this.load(); //刷新表格数据
      })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>

<style scoped>

</style>