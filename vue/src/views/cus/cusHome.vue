<template>
  <div style="width: 1200px;margin: auto">
    <el-carousel trigger="click" height="400px" :interval="3000" arrow="always" style="width:100%" >
      <el-carousel-item v-for="item in imgList" :key="item.name">
        <img :src="item.src" style="height:100%; width:100%;" alt="图片丢失了" :title="item.title" />
      </el-carousel-item>
    </el-carousel>
  </div>
  <div style="width: 80%;margin: 10px 400px 10px 400px ">
    <div style="width: 100%"><h1 style="font-size: 60px;color: dodgerblue">欢迎来到电子商城</h1></div>
    <br>
    <div style="width: 100%"><h1 style="font-size: 60px;color: dodgerblue">基于springboot-vue</h1></div>
    <br>
    <div style="width: 100%"><h1 style="font-size: 60px;color: dodgerblue">19网络工程梁浩潮制作</h1></div>
    <br>
    <div style="width: 100%"><h1 style="font-size: 60px;color: dodgerblue">201930342165</h1></div>
  </div>

</template>

<script>
export default {
  name: "cusHome",
  data() {
    return {
      imgList: [
        {
          name: "1",
          src: require("../../assets/pic1.jpg"),
          title: "1"
        },
        {
          name: "2",
          src: require("../../assets/pic2.jpg"),
          title: "2"
        },
        {
          name: "3",
          src: require("../../assets/pic3.jpg"),
          title: "3"
        },
      ]
    }
  }
}
</script>

<style scoped>

</style>