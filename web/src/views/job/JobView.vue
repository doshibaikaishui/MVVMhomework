<template>
  <ContentField>
    <!-- 搜索框 -->
    <nav class="navbar">
      <div class="container-fluid d-flex justify-content-center">
        <form class="d-flex justify-content-center w-100" role="search">
          <input
            class="form-control custom-input w-100"
            type="search"
            placeholder="搜索职位"
            v-model="searchQuery"
          />
          <button type="button" class="btn btn-primary" @click="searchJobs">
            搜索
          </button>
        </form>
      </div>
    </nav>

    <!-- 职位列表 -->
    <div class="job-list" style="min-height:600px;">
      <div
        class="job-item"
        v-for="job in filteredJobs"
        :key="job.id"
        @click="goToJobDetail(job.id)"
      >
        <h5>{{ job.title }}</h5>
        <p>{{ job.industry }} | {{ job.salary_min }} - {{ job.salary_max }}k | {{ job.location }}</p>
        <span>{{ job.experience }} | {{ job.education }}</span>
      </div>
    </div>
  </ContentField>
  <BottomBox></BottomBox>
</template>

<script>
import ContentField from "../../components/ContentField.vue";
import BottomBox from "@/components/bottombox/BottomBox.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    ContentField,
    BottomBox,
  },
  data() {
    return {
      searchQuery: "", // 搜索关键字
    };
  },
  computed: {
    ...mapGetters("job", ["filteredJobs"]),
  },
  methods: {
    searchJobs() {
      // 点击搜索按钮后更新 Vuex 中的 searchQuery
      this.$store.commit('job/setSearchQuery', this.searchQuery);
    },
    goToJobDetail(jobId) {
      this.$router.push(`/job/detail?job_id=${jobId}`);
    },
  },
  mounted() {
    this.$store.dispatch("job/loadJobs"); // 加载职位列表
  },
};
</script>

<style scoped>
.job-list {
  margin: 20px;
}
.job-item {
  padding: 15px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
  cursor: pointer;
  border-radius: 8px;
}
.job-item:hover {
  background-color: #f9f9f9;
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

.btn-primary {
  width: 70px;
  height: 54px;

}
</style>
