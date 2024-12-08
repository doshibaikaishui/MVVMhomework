<template>

    <div class="container mt-4">
        <h2 class="text-center text-primary">USER / Resume</h2>
    <h5 class="text-center text-muted mb-4">个人简历</h5>
    <div class="row">
        <LeftNav></LeftNav>
        <div class="col-9">

        
    <div class="container">
        <!-- Header Section -->
        <div class="resume-header">
          <img src="profile.jpg" alt="Profile Picture" id="profile-photo">
          <div>
            <h2 id="name">张三</h2>
            <p>性别: <span id="gender">男</span> | 年龄: <span id="age">22</span> | 学历: <span id="education">本科</span></p>
            <p>电话: <span id="phone">13522342234</span> | 邮箱: <span id="email">85069866@qq.com</span></p>
          </div>
        </div>
    
        <!-- Personal Advantages -->
        <div class="resume-section">
          <h3>个人优势 <span class="edit-btn text-primary" onclick="editSection('geren')">编辑</span></h3>
          <p id="personal-advantages">对待工作责任心强，积极主动...</p>
        </div>
    
        <!-- Job Intentions -->
        <div class="resume-section">
          <h3>求职意向 <span class="edit-btn text-primary" onclick="editSection('job')">编辑</span></h3>
          <ul id="job-intentions">
            <li>岗位: 前端工程师 | 地点: 深圳 | 薪资: 8-14K | 入职时间: 2个月</li>
            <li>岗位: web前端开发工程师 | 地点: 广州 | 薪资: 7-13K | 入职时间: 1个月</li>
          </ul>
        </div>
    
        <!-- Work Experience -->
        <div class="resume-section">
          <h3>工作经历 <span class="edit-btn text-primary" onclick="editSection('work')">添加</span></h3>
          <div id="work-experience">
            <p>公司: 迅雷网络</p>
            <p>职位: 前端工程师</p>
            <p>时间: 2018.12 - 2020.06</p>
            <p>描述: 参与开发...</p>
          </div>
        </div>
      </div>
    </div>
    </div>
</div>
  </template>
  
  <script>
    import $ from 'jquery'
    import LeftNav from '../../components/usercenter/LeftNav.vue'

  export default {
    components: {
        LeftNav,
    },
    name: "RemuseView",
    data() {
      return {
        user: {
          name: "",
          gender: "",
          phone: "",
          advantages: "",
          jobIntent: "",
          salary: ""
        }
      };
    },
    methods: {
      editPersonalInfo() {
        this.$router.push("/personal-info");
      },
      editAdvantages() {
        alert("跳转到编辑个人特长页面");
      },
      editJobIntentions() {
        alert("跳转到编辑求职意向页面");
      },
      fetchResume() {
        $.ajax({
          url: "http://127.0.0.1:3000/resume/info/",
          type: "get",
          headers: {
            Authorization: "Bearer " + this.$store.state.token
          },
          success: (resp) => {
            if (resp.error_message === "success") {
              this.user = { ...resp.data };
            } else {
              console.error("Error fetching resume:", resp.error_message);
            }
          },
          error: (err) => {
            console.error("Request failed:", err);
          }
        });
      }
    },
    created() {
      this.fetchResume();
    }
  };
  </script>
  
  <style scoped>
  .resume-header {
    display: flex;
    align-items: center;
    padding: 20px;
    background-color: #f8f9fa;
    border-bottom: 1px solid #dee2e6;
  }
  .resume-header img {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    margin-right: 20px;
  }
  .resume-section {
    margin: 20px 0;
  }
  .resume-section h3 {
    margin-bottom: 10px;
    font-size: 1.25rem;
  }
  .edit-btn {
    float: right;
    cursor: pointer;
  }
  </style>
  