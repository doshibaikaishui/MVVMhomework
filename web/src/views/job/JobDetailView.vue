<template>
  <ContentField style="min-height: 700px;">
    <div class="job-detail card p-4" v-if="job">
      <h3 class="card-title">{{ job.title }}</h3>
      <p class="card-text">{{ job.description }}</p>

      <!-- 使用 Bootstrap 的行和列来将数据分开并加大间距 -->
      <div class="mt-4">
        <p class="card-text">
          <strong>薪资：</strong>{{ job.salary_min }} - {{ job.salary_max }}k
        </p>
        <p class="card-text">
          <strong>地点：</strong>{{ job.location }}
        </p>
        <p class="card-text">
          <strong>经验要求：</strong>{{ job.experience }}
        </p>
        <p class="card-text">
          <strong>学历：</strong>{{ job.education }}
        </p>
      </div>
    </div>

    <!-- 判断是否显示投递简历按钮 -->
    <button 
      class="btn btn-primary mt-4" 
      v-if="this.$store.company_name==null" 
      @click="applyForJob"
    >
      投递简历
    </button>
  </ContentField>

  <BottomBox></BottomBox>
</template>

<script>
import ContentField from "../../components/ContentField.vue";
import { mapGetters } from "vuex";
import BottomBox from "@/components/bottombox/BottomBox.vue";

export default {
  props: ['jobId'], // 接收 jobId 参数
  components: {
    ContentField,
    BottomBox,
  },
  computed: {
    ...mapGetters("job", ["jobDetail"]), // 从 Vuex 映射职位详情
    ...mapGetters("user", ["getinfo"]), // 从 Vuex 映射用户信息
    job() {
      return this.jobDetail; // 从 Vuex 获取当前职位详情
    },
  },
  data() {
    return {
      comments: [], // 留言列表
      newComment: "", // 新留言
    };
  },
  methods: {
    // 投递简历的操作
    applyForJob() {
      const userInfo = this.$store.state.user; // 直接访问 state
      if (!userInfo.is_login) {
        alert("请先登录");
        return;
      }
      if (userInfo.company_name) {
        alert("只有求职者可以投递简历");
        return;
      }

      this.$store.dispatch("job/applyForJob", {
        success: (res) => {
          alert("投递成功");
          console.log("投递成功", res);
        },
        error: (err) => {
          alert("投递失败");
          console.error("投递失败", err);
        },
      });
    },

    // 获取评论
    getComments() {
      console.log("获取评论");
    },

    // 发送评论
    postComment() {
      console.log("发表了");
    },
  },
  mounted() {
    // 加载职位详情
    this.$store.dispatch("getinfo", {
      success: (resp) => {
        console.log("用户信息加载成功:", resp);
      },
      error: (err) => {
        console.error("加载用户信息失败:", err);
      },
    });
    this.$store.dispatch("job/loadJobDetail", this.jobId); // 加载职位详情
    this.getComments(); // 获取评论
  },
};
</script>

<style scoped>
.comment-section {
  margin-top: 20px;
}

textarea {
  width: 100%;
  height: 80px;
  margin-top: 10px;
}

button {
  margin-top: 20px; /* 增加按钮的间距 */
}

/* 调整各项信息的间距 */
.card-text {
  margin-bottom: 12px;
}
</style>
