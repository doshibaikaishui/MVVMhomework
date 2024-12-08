<template>
    <div class="container mt-4">
      <h2 class="text-center text-primary">USER / Apply</h2>
      <h5 class="text-center text-muted mb-4">简历投递</h5>
  
      <div v-if="alertMessage" class="alert alert-success alert-dismissible fade show mt-3" role="alert">
        {{ alertMessage }}
        <button type="button" class="btn-close" @click="closeAlert"></button>
      </div>
  
      <div class="row">
        <LeftNav></LeftNav>
  
      </div>
    </div>
  </template>
  
  <script>

  import LeftNav from '../../components/usercenter/LeftNav.vue'
  
  export default {
    components: {
      LeftNav,
    }
}
  </script>
  
  <style scoped>
  
  </style>
  