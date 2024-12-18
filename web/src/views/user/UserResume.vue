<template>
    <ContentField>
      <div class="container mt-4">
        <h2 class="text-center text-primary">求职者简历详情</h2>
  
        <!-- 个人信息 -->
        <div class="card mt-4">
          <div class="card-header">个人信息</div>
          <div class="card-body">
            <p><strong>姓名:</strong> {{ user.realName }}</p>
            <p><strong>联系方式:</strong> {{ user.phone }}</p>
            <p><strong>邮箱:</strong> {{ user.email }}</p>
            <p><strong>性别:</strong> {{ user.gender }}</p>
            <p><strong>学历:</strong> {{ user.degree }}</p>
            <p><strong>年龄:</strong> {{ user.age }}</p>
          </div>
        </div>
  
        <!-- 简历信息 -->
        <div class="card mt-4">
          <div class="card-header">简历信息</div>
          <div class="card-body">
            <p><strong>工作经验:</strong> {{ resume.experience }}</p>
            <p><strong>个人优势:</strong> {{ resume.advantages }}</p>
            <p><strong>教育经历:</strong> {{ resume.school }}</p>
            <p><strong>求职意向:</strong> {{ resume.intentions }}</p>
          </div>
        </div>
  
        <!-- 招聘官评价 -->
        <div class="card mt-4">
          <div class="card-header">招聘官评价</div>
          <div class="card-body">
            <ul class="list-group mt-3">
              <li v-for="comment in comments" :key="comment.id" class="list-group-item">
                <strong>{{ comment.username }}</strong> ({{  formatTime(comment.createTime)  }})
                <div>{{ comment.content }}</div>
                <div class="star-rating">
                  <span v-for="star in 5" :key="star" :class="['star', { 'filled': comment.rating >= star }]">&#9733;</span>
                </div>
              </li>
            </ul>
  
            <h5 class="mt-4">评价</h5>
            <div class="star-rating">
              <span v-for="star in 5" :key="star" @click="setRating(star)" :class="['star', { 'filled': rating >= star }]">&#9733;</span>
            </div>
            <textarea class="form-control mt-2" v-model="newComment" placeholder="写下您的评价"></textarea>
            <button class="btn btn-primary mt-2" @click="postComment">发表评价</button>
          </div>
        </div>
  
        <button class="btn btn-secondary mt-3" @click="$router.back()">返回</button>
      </div>
    </ContentField>
    <BottomBox></BottomBox>
  </template>
  
  <script>
  import $ from "jquery";
  import BottomBox from "@/components/bottombox/BottomBox.vue";
  import ContentField from "../../components/ContentField.vue";
  
  export default {
    components: {
      BottomBox,
      ContentField,
    },
    data() {
      return {
        user: {}, // 保存求职者个人信息
        resume: {}, // 保存求职者简历信息
        comments: [], // 保存评价列表
        newComment: "", // 新评价内容
        rating: 0, // 当前评价星级
      };
    },
    methods: {
      formatTime(createTime) {
    const date = new Date(createTime); // 将时间字符串转为 Date 对象
    return date.toLocaleString(); // 转换为本地时区的日期和时间格式
  },
      loadUserAndResumeDetail() {
        const userId = this.$route.query.id;
  
        // 加载个人信息
        $.ajax({
          url: `http://127.0.0.1:3000/user/account/info/${userId}`,
          type: "GET",
          headers: {
            Authorization: "Bearer " + this.$store.state.user.token,
          },
          success: (response) => {
            this.user = response;
          },
          error: (err) => {
            console.error("加载个人信息失败", err);
          },
        });
  
        // 加载简历信息
        $.ajax({
          url: `http://127.0.0.1:3000/resume/detail/${userId}`,
          type: "GET",
          headers: {
            Authorization: "Bearer " + this.$store.state.user.token,
          },
          success: (response) => {
            this.resume = response;
          },
          error: (err) => {
            console.error("加载简历信息失败", err);
          },
        });
  
        // 加载评价列表
        this.loadComments(userId);
      },
      loadComments(userId) {
        $.ajax({
          url: `http://127.0.0.1:3000/evaluation/list`,
          type: "GET",
          headers: {
            Authorization: "Bearer " + this.$store.state.user.token,
          },
          data: {userId},
          success: (response) => {
            this.comments = response;
          },
          error: (err) => {
            console.error("加载评论失败", err);
          },
        });
      },
      setRating(star) {
        this.rating = star;
      },
      postComment() {
        const userId = this.$route.query.id;
        const recruiterId = this.$store.state.user.id;
        const username = this.$store.state.user.username;

  
        $.ajax({
          url: "http://127.0.0.1:3000/evaluation/add",
          type: "POST",
          headers: {
            Authorization: "Bearer " + this.$store.state.user.token,
          },
          data: {
            userId,
            recruiterId,
            content: this.newComment,
            rating: this.rating,
            username,
            
          },
          success: () => {
            this.newComment = "";
            this.rating = 0;
            this.loadComments(userId);
          },
          error: (err) => {
            console.error("添加评论失败", err);
          },
        });
      },
    },
    mounted() {
      this.loadUserAndResumeDetail();
    },
  };
  </script>
  
  <style scoped>
  .comment-section {
    margin-top: 20px;
  }
  
  .star-rating {
    display: flex;
    font-size: 1.5rem;
    color: #ccc;
  }
  
  .star {
    cursor: pointer;
    margin-right: 5px;
  }
  
  .star.filled {
    color: #ffcc00;
  }
  </style>
  