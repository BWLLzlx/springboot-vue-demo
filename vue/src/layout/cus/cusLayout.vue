<template>
  <!--头部-->
  <cusHeader />
  <!--边栏-->
  <cusAside />
  <!--主体-->
  <router-view style="flex: 1"/>
</template>

<script>


import cusHeader from "../../components/cus/cusHeader";
import cusAside from "../../components/cus/cusAside";

export default {
  name: "Layout",
  components: {
    cusHeader,
    cusAside,

  }
}
</script>

<style scoped>

</style>