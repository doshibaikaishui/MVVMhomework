<template>
  <ContentField>
    <nav class="navbar">
      <div class="container-fluid  d-flex justify-content-center ">
        <form class="d-flex justify-content-center w-100 " role="search">
          <input class="form-control custom-input w-100 " type="search" placeholder="搜索职位、公司" aria-label="Search">
          <router-link class="btn btn-primary" :to="{name: 'job_index'}" style="padding-top: 13px;">搜索</router-link>
        </form>
      </div>
    </nav>

<!-- 热搜岗位模块 -->
<div class="container mt-4">
  <div class="search-hot d-flex flex-wrap justify-content-center">
    <b class="me-4" style="color: black;">热门职位:</b>
    <a href="#" class="btn btn-link me-4">Java</a>
    <a href="#" class="btn btn-link me-4">测试工程师</a>
    <a href="#" class="btn btn-link me-4">运维工程师</a>
    <a href="#" class="btn btn-link me-4">前端开发工程师</a>
    <a href="#" class="btn btn-link me-4">产品经理</a>
    <a href="#" class="btn btn-link me-4">平面设计</a>
    <a href="#" class="btn btn-link me-4">CAD绘图员</a>
  </div>
</div>
  </ContentField>
  
  
<div class="container" style="margin-top: 10px;" >
  <div class="row ">
    <div class="col-3">
          <table class="table table-hover" >
            <tbody>
            <tr>
            <th scope="row">
              <a href="#" class="list-group-item" data-target="detail1">互联网/IT</a>
            </th>
            </tr>
            <tr>
            <th scope="row">
              <a href="#" class="list-group-item" data-target="detail2">金融</a>
            </th>
            </tr>
            <tr>
            <th scope="row">
              <a href="#" class="list-group-item" data-target="detail3">房地产/建筑</a>
            </th>
            </tr>
            <tr>
              <th scope="row">
                <a href="#" class="list-group-item" data-target="detail4">贸易/零售/物流</a>
              </th>
              </tr>
              <tr>
                <th scope="row">
                  <a href="#" class="list-group-item" data-target="detail5">教育/传媒/广告</a>
                </th>
                </tr>
                <tr>
                  <th scope="row">
                    <a href="#" class="list-group-item" data-target="detail3">房地产/建筑</a>
                  </th>
                  </tr>
                  <tr>
                    <th scope="row">
                      <a href="#" class="list-group-item" data-target="detail3">服务业</a>
                    </th>
                    </tr>
                    <tr>
                      <th scope="row">
                        <a href="#" class="list-group-item" data-target="detail3">市场/销售</a>
                      </th>
                      </tr>
            </tbody>
            </table>
      </div>

    <div class="col-9">
          <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
            <div class="carousel-item active "  data-bs-interval="3000">
            <img src="../../assets/img/image1.png" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
            <img src="../../assets/img/image2.png" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item">
            <img src="../../assets/img/image3.png" class="d-block w-100" alt="...">
            </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
            </button>
            </div>
      </div>
  </div>
</div>

<div class="container" style="margin-top: 100px;">
  <span style="text-align: center;"><h1>热门职位</h1></span>
  <div class="row" style="margin-top: 55px;">
    <!-- 导航栏 -->
    <JobRed></JobRed>
  </div>
</div>

<div class="container" style="margin-top: 100px;">
  <span style="text-align: center;"><h1>热门企业</h1></span>
  <div class="row" style="margin-top: 55px;">
    <!-- 导航栏 -->
    <CompanyRed></CompanyRed>
  </div>
</div>

<BottomBox></BottomBox>

  </template>
  
<script>
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';
import 'bootstrap-icons/font/bootstrap-icons.css';
import ContentField from '../../components/ContentField.vue'
import JobRed from '@/components/jobrecommend/JobRed.vue';
import CompanyRed from '@/components/companyrecommend/CompanyRed.vue'
import BottomBox from '@/components/bottombox/BottomBox.vue';
import { Carousel } from 'bootstrap';

export default {
  components : {
    ContentField,
    JobRed,
    CompanyRed,
    BottomBox
  },
  onMounted() {
    new Carousel(document.querySelector('#carouselExampleIndicators'));
  },
};
  
</script>

<style scoped>

.btn-primary {
  width: 70px;
  height: 54px;

}

.custom-input {
  width: 900px;
  height: 54px;
  border: 2px solid #00bebd;

  border-bottom-left-radius: 20px;
  border-top-left-radius: 20px;
  border-end-end-radius: 0px;
  border-top-right-radius: 0px;
}

.search-hot a {
  background: rgba(255, 255, 255, .8);
  border-radius: 6px;
  color: #00a6a7;
  font-size: 14px;
  line-height: 20px;
  padding: 2px 8px;
  text-decoration: none;
}

.search-hot a:hover {
  background-color: rgb(0,180,179);
  color: white;
}  



.btn  {
  border-bottom-left-radius: 0px;
  border-top-left-radius: 0px;
  border-end-end-radius: 20px;
  border-top-right-radius: 20px;
}

.col-3, .col-9 {
  padding: 0;
  margin: 0;
  overflow: hidden; /* 确保内部内容不溢出 */
}

.col-3 {
  border-bottom-left-radius: 10px;
  border-top-left-radius: 10px;
  border-end-end-radius: 0px;
  border-top-right-radius: 0px;
}

.col-9 {
  border-bottom-left-radius: 0px;
  border-top-left-radius: 0px;
  border-end-end-radius: 10px;
  border-top-right-radius: 10px;
}

.col-3 > * {
  flex-grow: 1;
  height: 100%; /* 确保填满 */
}


.table .list-group-item {
  text-align: left; /* 列表文字水平居中 */
  margin: 12px 20px; /* 添加垂直间距 */
}








</style>
  