<template>
  <div>
    <!-- 导航栏 -->
    <div class="nav" style="text-align: center;">
      <div class="col-4">
        <span 
          @click="setActiveTab('金融')" 
          class="btn" 
          :class="{ active: activeTab === '金融' }">
          金融
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
      <div v-if="activeTab === '金融'" class="row">
        <div v-for="job in financeJobs" :key="job.id" class="col-12 col-md-4 mb-3">
          <div class="job-card text-center">
            <h5 class="job-title">{{ job.title }}</h5>
            <p class="job-details">{{ job.details }}</p>
            <span class="badge salary-badge">{{ job.salary }}</span>
          </div>
        </div>
      </div>
      <div v-if="activeTab === '服务业'" class="row">
        <div v-for="job in serviceJobs" :key="job.id" class="col-12 col-md-4 mb-3">
          <div class="job-card text-center">
            <h5 class="job-title">{{ job.title }}</h5>
            <p class="job-details">{{ job.details }}</p>
            <span class="badge salary-badge">{{ job.salary }}</span>
          </div>
        </div>
      </div>
      <div v-if="activeTab === '客服/运营'" class="row">
        <div v-for="job in customerJobs" :key="job.id" class="col-12 col-md-4 mb-3">
          <div class="job-card text-center">
            <h5 class="job-title">{{ job.title }}</h5>
            <p class="job-details">{{ job.details }}</p>
            <span class="badge salary-badge">{{ job.salary }}</span>
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
      activeTab: '金融', // 默认显示的内容
      financeJobs: [
        { id: 1, title: '金融分析师', details: '经验不限，学历不限', salary: '12-24K' },
        { id: 2, title: '投资经理', details: '需经验2年以上', salary: '15-30K' },
        { id: 3, title: '资产管理经理', details: '需经验3年以上', salary: '20-40K' },
      ],
      serviceJobs: [
        { id: 4, title: '酒店经理', details: '需管理经验', salary: '8-12K' },
        { id: 5, title: '餐饮服务员', details: '经验不限', salary: '5-8K' },
        { id: 6, title: '客户关系经理', details: '需管理经验', salary: '10-15K' },
      ],
      customerJobs: [
        { id: 7, title: '客服专员', details: '在线服务', salary: '6-9K' },
        { id: 8, title: '运营助理', details: '需熟悉电商平台', salary: '7-10K' },
        { id: 9, title: '产品运营专员', details: '需经验1年以上', salary: '8-12K' },
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
.nav .col-4 {
  border-bottom: 1px solid rgb(242, 244, 247);
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

/* 职位卡片样式 */
.job-card {
  border: 1px solid #e3e3e3;
  border-radius: 8px;
  padding: 15px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  background-color: #fff;
}

.job-card:hover {
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.job-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.job-details {
  color: #666;
  font-size: 14px;
  margin-bottom: 10px;
}

.salary-badge {
  background-color: rgb(1, 190, 189);
  color: white;
  padding: 5px 10px;
  border-radius: 20px;
}
</style>
