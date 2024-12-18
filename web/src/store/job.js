import $ from "jquery";

export default {
  namespaced: true,
  state: {
    jobs: [], // 职位数据列表
    job: [], // 当前职位详情
  },
  getters: {
    filteredJobs(state) {
      // 如果没有 searchQuery，则使用空字符串，避免 toLowerCase 错误
      const searchQuery = state.searchQuery || '';
      
      return state.jobs.filter((job) => {
        const title = job && typeof job.title === 'string' ? job.title : '';
        return title.toLowerCase().includes(searchQuery.toLowerCase());
      });
    },
    jobDetail: (state) => {
      return state.job; // 返回当前职位详情
    },
  },
  
  mutations: {
    setJobs(state, jobs) {
      state.jobs = jobs;
    },
    setJob(state, job) {
        state.job = job; // 设置当前职位详情
      },
      setSearchQuery(state, searchQuery) {
        state.searchQuery = searchQuery;
      },
  },
  actions: {
    loadJobs({ commit, rootState }) {
      $.ajax({
        url: `http://127.0.0.1:3000/job/list`,  // 修改为带有 userId 的 URL
        type: "GET",
        headers: {
          Authorization: "Bearer " + rootState.user.token,  // 保持身份验证
        },
        success: (response) => {
          // 假设后端返回的是该用户的职位数组
          commit(
            "setJobs",
            response.map((job) => ({
              id: job.id,
              title: job.title,
              industry: job.industry,
              salary_min: job.salaryMin, // 注意字段名需要匹配后端
              salary_max: job.salaryMax,
              experience: job.experience,
              education: job.education,
              type: job.type,
              location: job.location,
              description: job.description,
              status: job.status,
              create_time: job.createTime,
            }))
          );
        },
        error: (error) => {
          console.error("请求失败", error);
        },
      });
    },
    // 获取当前招聘官发布的职位
  loadMyJobs({ commit, rootState }) {
    const userId = rootState.user.id; // 获取当前招聘官的 ID
    $.ajax({
      url: `http://127.0.0.1:3000/job/list/${userId}`, // 获取该招聘官发布的职位
      type: "GET",
      headers: {
        Authorization: "Bearer " + rootState.user.token, // 保持身份验证
      },
      success: (response) => {
        commit(
          "setJobs",
          response.map((job) => ({
            id: job.id,
            title: job.title,
            industry: job.industry,
            salary_min: job.salaryMin,
            salary_max: job.salaryMax,
            experience: job.experience,
            education: job.education,
            type: job.type,
            location: job.location,
            description: job.description,
            status: job.status,
            create_time: job.createTime,
          }))
        );
      },
      error: (error) => {
        console.error("请求失败", error);
      },
    });
  },
  // 根据职位 ID 获取职位详情
  loadJobDetail({ commit, rootState }, jobId) {
    $.ajax({
      url: `http://127.0.0.1:3000/job/detail?job_id=${jobId}`, // 根据 job_id 获取职位详情
      type: "GET",
      headers: {
        Authorization: "Bearer " + rootState.user.token, // 使用 token 添加 Authorization 头
      },
      success: (response) => {
        commit("setJob", {
          id: response.id,
          title: response.title,
          industry: response.industry,
          salary_min: response.salaryMin,
          salary_max: response.salaryMax,
          experience: response.experience,
          education: response.education,
          type: response.type,
          location: response.location,
          description: response.description,
          status: response.status,
          create_time: response.createTime,
          userId: response.userId,
        });
      },
      error: (error) => {
        console.error("请求失败", error);
      },
    });
    },

    applyForJob({ success, error , rootState, state}) {
      const userId = rootState.user.id; // 获取当前用户的 IDw
      const jobId = state.job.id;
      const recruiterId = state.job.userId;
      const jobTitle = state.job.title;
      console.log(userId," ",jobId," ",recruiterId, "", jobTitle);
      $.ajax({
          url: `http://127.0.0.1:3000/jobApplication/apply`,
          type: "POST",
          headers: {
            Authorization: "Bearer " + rootState.user.token, // 使用 token 添加 Authorization 头
          },
          data: {
              userId,
              jobId,
              recruiterId,
              jobTitle,
          },
          success: (response) => {
              if (response.error_message === "success") {
                  alert("简历投递成功！");
                  if (typeof success === "function") {
                      success(response);
                  }
              } else {
                  alert(response.message || "投递失败，请稍后重试");
                  if (typeof error === "function") {
                      error(response);
                  }
              }
          },
          error: (err) => {
              console.error("简历投递请求失败", err);
              alert("请求失败，请稍后重试");
              if (typeof error === "function") {
                  error(err);
              }
          },
      });
  },

   // 添加职位
   addJob({ rootState }, { jobData, success, error }) {
    $.ajax({
      url: "http://127.0.0.1:3000/job/add",
      type: "POST",
      headers: {
        Authorization: "Bearer " + rootState.user.token,
      },
      contentType: "application/json",
      data:  JSON.stringify(jobData),
      success: (response) => {
        if (response === "success") {
          if (typeof success === "function") success(response);
        } else {
          if (typeof error === "function") error(response);
        }
      },
      error: (err) => {
        console.error("职位发布请求失败", err);
        if (typeof error === "function") error(err);
      },
    });
  },
  },
};

