<template>
  <div style="padding: 0 100px;">

    <!--功能区域-->
    <div style="display: flex">
      <!--搜索id-->
      <div style="margin: 10px 0 ;width: 1000px">
        <el-input v-model="goodsIdSearch" placeholder="商品编号" style="width: 150px; margin-left: 5px; flex: 1;" clearable />
        <el-input v-model="cusIdSearch" placeholder="用户编号" style="width: 150px; margin-left: 5px; flex: 1;" clearable />
        <el-button type="primary" style="margin-left: 5px; flex: 1;" @click="load">查询</el-button>
      </div>
      <!--空-->
      <div style="flex: 1; "></div>
<!--      新增-->
<!--      <div>-->
<!--        <el-button style="margin: 10px 0" type="primary" @click="add">添加订单</el-button>-->
<!--      </div>-->
    </div>

    <!--表格区域-->
    <div>
      <!--页表-->
      <el-table :data="tableData" stripe border style="width: 100%">
        <!--普通内容-->
        <el-table-column prop="id" label="订单编号" sortable />
        <el-table-column prop="userId" label="用户编号" sortable />
        <el-table-column prop="goodsId" label="商品编号" sortable />
        <el-table-column prop="goodsName" label="商品名称" sortable />
        <el-table-column prop="price" label="单价" sortable />
        <el-table-column prop="createTime" label="登记时间" sortable />
        <!--封面部分-->
        <el-table-column prop="status" label="订单状态" sortable />

        <el-table-column fixed="right" prop="operation" label="操作" width="80">
          <template #default="scope">
            <el-button type="text" @click="buy(scope.row)" size="small">付款</el-button>
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

    <!--提示框-->
    <!--提示款区域-->
    <div>
      <el-dialog v-model="dialogVisible" title="付款" width="600px">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户编号">
            <el-input v-model="form.userId" style="width:60%" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品编号">
            <el-input v-model="form.goodsId" style="width:60%" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品名称">
            <el-input v-model="form.goodsName" style="width:60%" disabled></el-input>
          </el-form-item>
          <el-form-item label="商品价格">
            <el-input v-model="form.price" style="width:60%" disabled></el-input>
          </el-form-item>
          <el-form-item label="购买时间">
            <el-date-picker v-model="form.createTime" disabled value-format="YYYY-MM-DD" type="date" style="width:60%" clearable></el-date-picker>
          </el-form-item>
          <el-form-item label="订单状态">
            <el-select v-model="form.status" placeholder="请选择" style="width:60%" disabled>
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <img style="width: 200px;" src="../../assets/QRcode.png">
            <div style="width: 300px;">
              <span style="size: 8px">目前仅支持微信转账，请扫下方二维码支付并注明订单编号，待管理员确认收款后即可确认发货</span>
            </div>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确定</el-button>
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
  name: "cusList",
  data() {
    return {
      form: {},
      dialogVisible:false,
      goodsIdSearch:'',
      cusIdSearch:'',
      total: 0,
      pageSize: 10,
      currentPage: 1,
      tableData: [],
      options: [
        // label是显示出来的部分，value是真正的值
        { value: '未支付', label: '未支付' },
        { value: '已付款', label: '已付款'},
        { value: '已发货', label: '已发货' },
      ],
    }
  },
  created() {

    this.load()
  },
  methods:{
    load() {
      request.get("/api/cus/cusList", {
        params:{
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          goodsIdSearch: this.goodsIdSearch,
          cusIdSearch: this.cusIdSearch,
          cusId: this.form.userId,
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
    buy(row) {
      this.form = JSON.parse(JSON.stringify(row))//避免编辑了之后点取消
      this.dialogVisible = true
      this.$nextTick(() => { //因为点编辑之前那个组件都不存在，那就没法获取ref这个组件，没这个组件还去调用会报错，用nextTick可以解决调用时元素不存在的错误
        this.$refs['upload'].clearFiles()
      })
    },
    handleDelete(id){

    },
  }
}
</script>

<style scoped>

</style>