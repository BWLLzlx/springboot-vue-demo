<template>
  <!--头部-->
  <manHeader />

  <!--主体-->
  <div style="display: flex ">

    <!--侧边栏-->
    <manAside />
    <!--内容-->
    <router-view style="flex: 1"/>

  </div>
</template>

<script>

import manHeader from "../../components/man/manHeader";
import manAside from "../../components/man/manAside";

export default {
  name: "manLayout",
  components: {
    manHeader,
    manAside
  }
}
</script>

<style scoped>

</style>