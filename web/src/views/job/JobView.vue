<template>
  <ContentField>
    <nav class="navbar">
      <div class="container-fluid  d-flex justify-content-center ">
        <form class="d-flex justify-content-center w-100 " role="search">
          <input class="form-control custom-input w-100 " type="search" placeholder="搜索职位" aria-label="Search">
          <button type="submit" class="btn btn-primary " >搜索</button>
        </form>
      </div>
    </nav>
  </ContentField>
  

<BottomBox></BottomBox>

  </template>
  
<script>
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap';
import 'bootstrap-icons/font/bootstrap-icons.css';
import ContentField from '../../components/ContentField.vue'
import BottomBox from '@/components/bottombox/BottomBox.vue';

export default {
  components : {
    ContentField,
    BottomBox
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

.btn  {
    border-bottom-left-radius: 0px;
    border-top-left-radius: 0px;
    border-end-end-radius: 20px;
    border-top-right-radius: 20px;
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


</style>
  