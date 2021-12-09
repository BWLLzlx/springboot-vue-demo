<template>
  <div style="width: 1200px;margin: auto">
    <el-carousel trigger="click" height="400px" :interval="3000" arrow="always" style="width:100%" >
      <el-carousel-item v-for="item in imgList" :key="item.name">
        <img :src="item.src" style="height:100%; width:100%;" alt="图片丢失了" :title="item.title" />
      </el-carousel-item>
    </el-carousel>
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