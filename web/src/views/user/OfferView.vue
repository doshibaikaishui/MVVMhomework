<template>
  <ContentField style="min-height: 700px;">
  <div class="container mt-4">
    <h2 class="text-center text-primary">用户 / 投递状态</h2>
    <h5 class="text-center text-muted mb-4">投递情况</h5>

    <div v-if="alertMessage" class="alert alert-success alert-dismissible fade show mt-3" role="alert">
      {{ alertMessage }}
      <button type="button" class="btn-close" @click="closeAlert"></button>
    </div>

    <div class="row">
      <LeftNav></LeftNav>
      <div class="col-9">
        <table class="table">
          <thead>
            <tr>
              <th>职位名称</th>
              <th>投递状态</th>
              <th>投递时间</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="application in applications" :key="application.id">
              <td>{{ application.jobTitle }}</td>
              <td>
                <!-- 根据投递状态动态设置样式 -->
                <span :class="getStatusClass(application.resumeStatus)">
                  {{ application.resumeStatus }}
                </span>
              </td>
              <td>{{ application.createTime }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</ContentField>
  <BottomBox></BottomBox>
</template>

<script>
import LeftNav from '../../components/usercenter/LeftNav.vue'
import $ from 'jquery'
import BottomBox from '@/components/bottombox/BottomBox.vue';
import ContentField from '../../components/ContentField.vue'

export default {
  components: {
    LeftNav,
    BottomBox,
    ContentField,
  },
  data() {
    return {
      applications: [],  // 存储职位申请记录
      alertMessage: '',  // 弹出消息
    };
  },
  mounted() {
    this.fetchApplications();
  },
  methods: {
    fetchApplications() {
    const userId = this.$store.state.user.id;
    $.ajax({
      url: `http://127.0.0.1:3000/jobApplication/list/${userId}`,
      type: "GET",
      headers: {
        Authorization: "Bearer " + this.$store.state.user.token,
      },
      success: (response) => {
        // 将数据库返回的时间转换为本地时间
        this.applications = response.applications.map(application => {
          application.createTime = new Date(application.createTime).toLocaleString(); // 转换为本地时间
          return application;
        });
      },
      error: (error) => {
        console.error("请求失败", error);
      }
    });
  },
    closeAlert() {
      this.alertMessage = '';
    },
    // 根据投递状态返回不同的 Bootstrap 样式
    getStatusClass(status) {
      if (status === 'Pending') {
        return 'badge bg-warning text-dark';  // 黄色的背景，黑色文字
      } else if (status === '通过') {
        return 'badge bg-success';  // 绿色背景
      } else if (status === '未通过') {
        return 'badge bg-danger';  // 红色背景
      } else {
        return 'badge bg-secondary';  // 默认的灰色背景
      }
    }
  }
}
</script>

<style scoped>
/* 你可以根据需要添加一些自定义样式 */
</style>
