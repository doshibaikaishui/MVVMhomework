
<template>
    <!-- 左侧导航栏 -->
<div class="col-3">
    <ul class="list-group">
        <router-link class="list-group-item" active-class="active" :to="{name: 'boss_center'}" >职位管理</router-link>
        <router-link class="list-group-item" active-class="active" :to="{name: 'boss_resume'}">简历管理</router-link>
        <router-link class="list-group-item" active-class="active" :to="{name: 'boss_interview'}">面试</router-link>
    </ul>
  </div>

  </template>
  
  <script>
  </script>
  
  <style scoped>
 .list-group-item.active {
    
    color: lightgreen;
    
}

  </style>
  