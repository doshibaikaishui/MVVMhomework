<template>
  <div class="container mt-4">
    <h2 class="text-center text-primary">BOSS / CENTER</h2>
    <h5 class="text-center text-muted mb-4">简历管理</h5>

    <div class="row" style="min-height: 600px;">
      <LeftNav></LeftNav>
      <div class="col-9">
        <div class="resume-list">
          <h3>简历列表</h3>
          <table class="table table-bordered table-hover">
            <thead class="table-primary">
              <tr>
                <th>职位</th>
                <th>求职者姓名</th>
                <th>联系方式</th>
                <th>简历状态</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="application in jobApplications" :key="application.id">
                <td>{{ application.job_title }}</td>
                <td>{{ application.candidateName }}</td>
                <td>{{ application.contact }}</td>
                <td>
                  <span
                    class="badge"
                    :class="{
                      'bg-warning': application.resume_status === 'Pending',
                      'bg-success': application.resume_status === '通过',
                      'bg-danger': application.resume_status === '不通过'
                    }"
                  >
                    {{ application.resume_status }}
                  </span>
                </td>
                <td>
                  <button
                    class="btn btn-sm btn-info me-2"
                    @click="viewResume(application)"
                  >
                    查看
                  </button>
                  <button
                    class="btn btn-sm btn-success me-2"
                    @click="updateStatus(application, '通过')" 
                  >
                    通过
                  </button>
                  <button
                    class="btn btn-sm btn-danger"
                    @click="updateStatus(application, '不通过')" 
                  >
                    不通过
                  </button>
                </td>
                

              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

  </div>
  <BottomBox></BottomBox>
</template>

  
<script>
import $ from "jquery";
import LeftNav from "@/components/bosscenter/LeftNav.vue";
import BottomBox from "@/components/bottombox/BottomBox.vue";

export default {
  components: {
    LeftNav,
    BottomBox,
  },
  data() {
    return {
      jobApplications: [], // 保存简历投递列表
    };
  },
  
  methods: {
    // 加载简历投递列表
    loadJobApplications() {
      const recruiterId = this.$store.state.user.id;
  $.ajax({
    url: "http://127.0.0.1:3000/jobApplication/list", // 后端接口，获取招聘官的简历投递记录
    type: "GET",
    headers: {
      Authorization: "Bearer " + this.$store.state.user.token,
    },
    data: {
      recruiterId,
    },
    success: (response) => {
      this.jobApplications = response.applications; // 保存投递数据和简历详情
    },
    error: (err) => {
      console.error("加载简历投递列表失败", err);
    },
  });
},

    // 查看简历，跳转到详情页
    viewResume(application) {
      this.$router.push({
        name: "resume_detail",
        query: { id: application.userId }, // 携带求职者ID
      });
    },
    // 更新简历状态
    updateStatus(application, status) {
    const applicationId = application.applicationId; // 从传入的 application 对象获取 applicationId
    $.ajax({
      url: `http://127.0.0.1:3000/jobApplication/update/${applicationId}`,
      type: "POST",
      headers: {
        Authorization: "Bearer " + this.$store.state.user.token,
      },
      data: { resumeStatus: status },
      success: () => {
        alert("状态更新成功！");
        this.loadJobApplications(); // 刷新投递记录
      },
      error: (err) => {
        console.error("更新状态失败", err);
        alert("状态更新失败，请稍后重试！");
      },
    });
  },

  },
  mounted() {
    this.loadJobApplications(); // 组件挂载后加载数据
  },
};
</script>

<style scoped>
.table {
  margin-top: 20px;
}
.table-primary {
  background-color: #f0f8ff;
}
.badge {
  font-size: 14px;
}
</style>


  