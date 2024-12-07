<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>

import NavBar from "@/components/NavBar.vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap";
import { onMounted } from 'vue';
import { useStore } from 'vuex';

export default {
  components:{
    NavBar
  },

  setup() {
    const store = useStore();

    onMounted(() => {
      if (!store.state.user.is_login) {
        store.dispatch('fetchUser'); // 页面加载时拉取用户信息
      }
    });
  }
}
</script>


<style>
body {
  
  background: linear-gradient(rgb(224,242,244), rgb(255,255,255));
  min-height: 100vh;
}
</style>
