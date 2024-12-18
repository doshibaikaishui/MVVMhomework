<template>
    <div class="container mt-4">
      <h2 class="text-center text-primary">发布职位</h2>
      <form @submit.prevent="createJob">
        <div class="form-group">
          <label for="title">职位标题</label>
          <input
            type="text"
            class="form-control"
            id="title"
            v-model="job.title"
            required
            placeholder="请输入职位标题"
          />
        </div>
        <div class="form-group">
          <label for="industry">所属行业</label>
          <input
            type="text"
            class="form-control"
            id="industry"
            v-model="job.industry"
            required
            placeholder="请输入行业"
          />
        </div>
        <div class="form-row">
          <div class="form-group col">
            <label for="salaryMin">最低薪资</label>
            <input
              type="number"
              class="form-control"
              id="salaryMin"
              v-model="job.salaryMin"
              required
              placeholder="最低薪资"
            />
          </div>
          <div class="form-group col">
            <label for="salaryMax">最高薪资</label>
            <input
              type="number"
              class="form-control"
              id="salaryMax"
              v-model="job.salaryMax"
              required
              placeholder="最高薪资"
            />
          </div>
        </div>
        <div class="form-group">
          <label for="experience">所需经验</label>
          <input
            type="text"
            class="form-control"
            id="experience"
            v-model="job.experience"
            required
            placeholder="请输入经验要求"
          />
        </div>
        <div class="form-group">
          <label for="education">所需学历</label>
          <input
            type="text"
            class="form-control"
            id="education"
            v-model="job.education"
            required
            placeholder="请输入学历要求"
          />
        </div>
        <div class="form-group">
          <label for="type">职位类型</label>
          <input
            type="text"
            class="form-control"
            id="type"
            v-model="job.type"
            required
            placeholder="请输入职位类型"
          />
        </div>
        <div class="form-group">
          <label for="location">工作地点</label>
          <input
            type="text"
            class="form-control"
            id="location"
            v-model="job.location"
            required
            placeholder="请输入工作地点"
          />
        </div>
        <div class="form-group">
          <label for="description">职位描述</label>
          <textarea
            class="form-control"
            id="description"
            v-model="job.description"
            required
            placeholder="请输入职位描述"
          ></textarea>
        </div>
        <button type="submit" class="btn btn-primary">发布</button>
        <router-link to="/boss/center" class="btn btn-secondary ml-2">取消</router-link>
      </form>
    </div>
  </template>
  
  
  <script>
  import { mapActions } from "vuex";
  
  export default {
    data() {
      return {
        job: {
          title: "",
          industry: "",
          salaryMin: 0,
          salaryMax: 0,
          experience: "",
          education: "",
          type: "",
          location: "",
          description: "",
          status: 0, // 默认未发布
        },
      };
    },
    methods: {
      ...mapActions("job", ["addJob"]),
      createJob() {
        // 将招聘官的 ID 自动附加
        const userId = this.$store.state.user.id;
        const jobData = { ...this.job, userId };
        this.addJob({
          jobData,
          success: () => {
            alert("职位发布成功！");
            this.$router.push("/boss/center");
          },
          error: () => {
            alert("职位发布失败，请稍后重试。");
          },
        });
      },
    },
  };
  </script>
  
  
  <style scoped>
  .container {
    max-width: 600px;
    margin: auto;
  }
  </style>
  
  