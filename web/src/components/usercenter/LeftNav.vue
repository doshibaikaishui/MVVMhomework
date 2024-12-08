
  <template>
    <!-- 左侧导航栏 -->
<div class="col-3">
    <ul class="list-group">
        <router-link class="list-group-item" active-class="active" :to="{name: 'user_center'}" >个人中心</router-link>
        <router-link class="list-group-item" active-class="active" :to="{name: 'RemuseView'}">个人简历</router-link>
        <router-link class="list-group-item" active-class="active" :to="{name: 'OfferView'}">投职情况</router-link>
        <router-link class="list-group-item" active-class="active" :to="{name: 'ApplyView'}">简历投递</router-link>
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
  