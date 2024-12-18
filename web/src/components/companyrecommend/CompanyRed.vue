<template>
  <div>
    <!-- 导航栏 -->
    <div class="nav" style="text-align: center;">
      <div class="col-4">
        <span 
          @click="setActiveTab('互联网/IT')" 
          class="btn" 
          :class="{ active: activeTab === '互联网/IT' }">
          互联网/IT
        </span>
      </div>
      <div class="col-4">
        <span 
          @click="setActiveTab('服务业')" 
          class="btn" 
          :class="{ active: activeTab === '服务业' }">
          服务业
        </span>
      </div>
      <div class="col-4">
        <span 
          @click="setActiveTab('客服/运营')" 
          class="btn" 
          :class="{ active: activeTab === '客服/运营' }">
          客服/运营
        </span>
      </div>
    </div>

    <!-- 内容区域 -->
    <div class="container" style="margin-top: 30px">
      <div v-if="activeTab === '互联网/IT'" class="row">
        <div v-for="company in internetCompanies" :key="company.id" class="col-12 col-md-4 mb-3">
          <div class="company-card text-center">
            <h5 class="company-name">{{ company.name }}</h5>
            <p class="company-details">{{ company.details }}</p>
            <span class="badge industry-badge">{{ company.industry }}</span>
          </div>
        </div>
      </div>
      <div v-if="activeTab === '服务业'" class="row">
        <div v-for="company in serviceCompanies" :key="company.id" class="col-12 col-md-4 mb-3">
          <div class="company-card text-center">
            <h5 class="company-name">{{ company.name }}</h5>
            <p class="company-details">{{ company.details }}</p>
            <span class="badge industry-badge">{{ company.industry }}</span>
          </div>
        </div>
      </div>
      <div v-if="activeTab === '客服/运营'" class="row">
        <div v-for="company in customerCompanies" :key="company.id" class="col-12 col-md-4 mb-3">
          <div class="company-card text-center">
            <h5 class="company-name">{{ company.name }}</h5>
            <p class="company-details">{{ company.details }}</p>
            <span class="badge industry-badge">{{ company.industry }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeTab: '互联网/IT', // 默认显示的内容
      internetCompanies: [
        { id: 1, name: '腾讯', details: '世界领先的互联网科技公司', industry: '互联网/IT' },
        { id: 2, name: '阿里巴巴', details: '全球最大的在线和移动商务公司之一', industry: '互联网/IT' },
        { id: 3, name: '网易', details: '互联网技术公司，提供互联网服务和内容', industry: '互联网/IT' },
      ],
      serviceCompanies: [
        { id: 4, name: '喜达屋酒店集团', details: '著名酒店管理公司', industry: '服务业' },
        { id: 5, name: '海底捞', details: '知名餐饮连锁，提供高品质服务', industry: '服务业' },
        { id: 6, name: '滴滴出行', details: '著名出行平台，提供打车、共享单车等服务', industry: '服务业' },
      ],
      customerCompanies: [
        { id: 7, name: '京东', details: '领先的电商平台，提供优质的客服和售后服务', industry: '客服/运营' },
        { id: 8, name: '淘宝', details: '全球最大的线上购物平台之一，提供良好的客服支持', industry: '客服/运营' },
        { id: 9, name: '美团', details: '生活外卖服务平台，提供多种服务', industry: '客服/运营' },
      ],
    };
  },
  methods: {
    setActiveTab(tabName) {
      this.activeTab = tabName;
    },
  },
};
</script>

<style scoped>
.nav .col-4{
  border-bottom: 1px solid rgb(242,244,247);
}

/* 导航栏样式 */
.nav .col-4 .btn {
  border: 0px;
  color: rgb(102, 102, 102);
  font-size: 21px; 
}

.nav .col-4 .btn:hover {
  color: rgb(1, 190, 189);
}

.nav .col-4 .btn.active {
  color: rgb(1, 190, 189); /* 高亮颜色 */
  font-weight: bold; /* 字体加粗 */
  border: 0px;
  border-bottom: 3px solid rgb(1, 190, 189); /* 下边框高亮 */
  border-radius: 0px;
}

/* 公司卡片样式 */
.company-card {
  border: 1px solid #e3e3e3;
  border-radius: 8px;
  padding: 15px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.company-card:hover {
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.company-name {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.company-details {
  color: #666;
  font-size: 14px;
  margin-bottom: 10px;
}

.industry-badge {
  background-color: rgb(1, 190, 189);
  color: white;
  padding: 5px 10px;
  border-radius: 20px;
}
</style>
