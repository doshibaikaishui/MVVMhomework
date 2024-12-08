<template>
    <nav class="navbar navbar-expand-lg bg-body-tertiary sticky-top" data-bs-theme="dark">
  <div class="container">
    <router-link class="navbar-brand " active-class="active" :to="{name: 'home'}" style="color: rgb(0,190,189);">职荐无忧</router-link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link " active-class="active" :to="{name: 'home_index'}">首页</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link " active-class="active" :to="{name: 'job_index'}">职位</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link " active-class="active" :to="{name: 'company_index'}">公司</router-link>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">我要招聘</a>
        </li>
        <li class="nav-item">
          <router-link class="nav-link " active-class="active" :to="{name: 'job_index'}">我要找工作</router-link>
        </li>
      </ul>
      <ul class="navbar-nav" v-if="$store.state.user.is_login">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ $store.state.user.username }}
          </a>
          <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdown">
            <li>
                <router-link class="dropdown-item" :to="{name: 'user_center'}">我的主页</router-link>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
          </ul>
        </li>
      </ul>
      <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'user_account_login' }" role="button">
            登录
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'user_account_register'}" role="button">
            注册
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</nav>
  </template>
  

  <script>
  import { useRoute } from 'vue-router'
  import { computed } from 'vue'
  import { useStore } from 'vuex';

  export default {
      setup() {
          const store = useStore();
          const route = useRoute();
          let route_name = computed(() => route.name)
  
          const logout = () => {
            store.dispatch("logout");
          }
  
          return {
              route_name,
              logout
          }

          
      }
  }
  </script>
  
  <style scoped>
  
  </style>
  