<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>

import NavBar from "@/components/NavBar.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";


export default {
  components:{
    NavBar
  },

  setup() {
    
  }
}
</script>


<style>
body {
  
  background: linear-gradient(rgb(224,242,244), rgb(255,255,255));
  min-height: 100vh;
}
</style>
