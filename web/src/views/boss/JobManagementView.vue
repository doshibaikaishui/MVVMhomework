<template>
  <div class="container mt-4">
    <h2 class="text-center text-primary">BOSS / CENTER</h2>
    <h5 class="text-center text-muted mb-4">职位管理</h5>

    <div class="row" style="min-height: 600px;">
      <LeftNav></LeftNav>
      <div class="col-9">
        <div class="content">
          <router-link class="btn btn-primary" :to="{ name:'boss_resume_create'}">发布新职位</router-link>
          <table class="table table-bordered table-hover">
            <thead class="table-primary">
              <tr>
                <th>职位名称</th>
                <th>行业</th>
                <th>薪资范围</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="job in jobs" :key="job.id">
                <td>{{ job.title }}</td>
                <td>{{ job.industry }}</td>
                <td>{{ job.salary_min }}-{{ job.salary_max }}</td>
                <td>
                  <button type="button" class="btn btn-primary" @click="editJob(job)" data-bs-toggle="modal" data-bs-target="#editJobModal">
                    编辑
                  </button>
                  <button class="btn btn-danger" style="margin-left: 10px;" @click="deleteJob(job.id)">删除</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  

  <!-- 编辑职位的弹框 -->
  <div class="modal fade" id="editJobModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="editJobModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="editJobModalLabel">编辑职位</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="submitEditJob">
            <div class="mb-3">
              <label for="jobTitle" class="form-label">职位名称</label>
              <input type="text" class="form-control" id="jobTitle" v-model="currentJob.title" required />
            </div>
            <div class="mb-3">
              <label for="jobIndustry" class="form-label">行业</label>
              <input type="text" class="form-control" id="jobIndustry" v-model="currentJob.industry" required />
            </div>
            <div class="mb-3">
              <label for="salaryRange" class="form-label">薪资范围</label>
              <div class="d-flex">
                <input type="number" class="form-control" id="salaryMin" v-model="currentJob.salaryMin" placeholder="最小薪资" required />
                <span class="mx-2">-</span>
                <input type="number" class="form-control" id="salaryMax" v-model="currentJob.salaryMax" placeholder="最大薪资" required />
              </div>
            </div>
            <div class="mb-3">
              <label for="jobLocation" class="form-label">地点</label>
              <input type="text" class="form-control" id="jobLocation" v-model="currentJob.location" required />
            </div>
            <div class="mb-3">
              <label for="jobDescription" class="form-label">职位描述</label>
              <textarea class="form-control" id="jobDescription" v-model="currentJob.description" required></textarea>
            </div>
            <div class="mb-3">
              <label for="jobExperience" class="form-label">经验要求</label>
              <input type="text" class="form-control" id="jobExperience" v-model="currentJob.experience" required />
            </div>
            <div class="mb-3">
              <label for="jobEducation" class="form-label">学历要求</label>
              <input type="text" class="form-control" id="jobEducation" v-model="currentJob.education" required />
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
              <button type="submit" class="btn btn-primary">保存更改</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>

  <BottomBox></BottomBox>
</template>

<script>
import $ from "jquery";


import LeftNav from "../../components/bosscenter/LeftNav.vue";
import BottomBox from "@/components/bottombox/BottomBox.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    LeftNav,
    BottomBox,
  },
  computed: {
    ...mapGetters("job", ["filteredJobs"]),
    jobs() {
      return this.filteredJobs;
    },
  },
  data() {
    return {
      currentJob: {},
    };
  },
  methods: {
    editJob(job) {
      this.currentJob = { ...job };
    },

    submitEditJob() {
  $.ajax({
    url: `http://127.0.0.1:3000/job/update`,
    type: "PUT",
    headers: {
      Authorization: "Bearer " + this.$store.state.user.token,
    },
    contentType: "application/json",
    data: JSON.stringify(this.currentJob),
    success: () => {
      alert("职位更新成功");
      this.loadJobs();

    },
    error: (err) => {
      alert("职位更新失败，请稍后再试");
      console.error("职位更新失败", err);
    },
  });
},


    deleteJob(id) {
      if (confirm("确定删除该职位吗？")) {
        $.ajax({
          url: `http://127.0.0.1:3000/job/delete/${id}`,
          type: "DELETE",
          headers: {
            Authorization: "Bearer " + this.$store.state.user.token,
          },
          success: () => {
            alert("删除成功");
            this.loadJobs();
          },
          error: (err) => {
            console.error("删除失败", err);
          },
        });
      }
    },

    loadJobs() {
      this.$store.dispatch("job/loadMyJobs").then(() => {
        console.log("职位数据已加载");
      });
    },
  },
  mounted() {
    this.loadJobs();
  },
};
</script>
