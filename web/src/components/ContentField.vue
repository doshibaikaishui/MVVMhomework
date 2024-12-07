<template>
    <div class="container content-field ">
      <div class="card no-bg">
        <div class="card-body">
          <slot></slot>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  </script>
  
  <style scoped>

  
  /* 移除背景和边框 */
  .no-bg {
    background-color: transparent;
    border: none;
  }
  

  </style>
  