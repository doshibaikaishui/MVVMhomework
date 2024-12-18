<template>
  <ComtentField>
    <div class="container mt-4" style="min-height: 600px;">
      <h2 class="text-center text-primary">创建简历</h2>
      <form @submit.prevent="createResumeHandler" class="p-4 border rounded shadow-sm bg-light">
        <div class="mb-3">
          <label for="advantages" class="form-label">个人优势</label>
          <textarea
            id="advantages"
            v-model="resume.advantages"
            class="form-control"
            rows="3"
            placeholder="请输入您的个人优势"
          ></textarea>
        </div>
        <div class="mb-3">
          <label for="experience" class="form-label">工作经验</label>
          <textarea
            id="experience"
            v-model="resume.experience"
            class="form-control"
            rows="3"
            placeholder="请输入您的工作经验"
          ></textarea>
        </div>
        <div class="mb-3">
          <label for="school" class="form-label">教育经历</label>
          <input
            type="text"
            id="school"
            v-model="resume.school"
            class="form-control"
            placeholder="请输入您的教育经历"
          />
        </div>
        <div class="mb-3">
          <label for="intentions" class="form-label">求职意向</label>
          <input
            type="text"
            id="intentions"
            v-model="resume.intentions"
            class="form-control"
            placeholder="请输入您的求职意向"
          />
        </div>
        <button type="submit" class="btn btn-primary w-100">创建简历</button>
      </form>
    </div>
  </ComtentField>
</template>

<script>
import ComtentField from '../../components/ContentField.vue';

export default {
  components: {
    ComtentField,
  },
  data() {
    return {
      resume: {
        advantages: '',
        experience: '',
        school: '',
        intentions: '',
        userId: null, // 初始值为空
      },
    };
  },
  computed: {
    userId() {
      // 假设用户信息存储在 Vuex 的 user 模块中
      return this.$store.state.user?.id || null;
    },
  },
  methods: {
    createResumeHandler() {
  console.log('发送到 Vuex 的数据:', this.resume); // 打印 Vuex 前的 resume 数据
  this.resume.userId = this.userId;

  if (!this.resume.userId) {
    alert('用户未登录，请先登录！');
    return;
  }

  this.$store.dispatch('resume/createResume', this.resume)
    .then((resp) => {
      console.log('响应数据:', resp); // 查看返回的响应
      if (resp.message) {
        alert(resp.message);
      } else {
        alert('创建失败: ' + (resp.error_message || '未知错误'));
      }
    })
    .catch((err) => {
      console.error('创建简历时出现错误:', err);
    });
},

  },
};
</script>

<style scoped>
/* 添加必要的样式以提升页面美观度 */
.container {
  max-width: 600px;
  margin: auto;
}
</style>
