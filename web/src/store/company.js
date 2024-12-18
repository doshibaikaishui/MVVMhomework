import $ from "jquery";

export default {
  namespaced: true,
  state: {
    companies: [],  // 初始化为空数组
    companyDetails: {}, // 当前公司详细信息
    comments: [],       // 当前公司评论列表
    loading: false,     // 加载状态
    searchQuery: "",  // 添加 searchQuery
  },
  getters: {
    filteredCompanies(state) {
      return state.companies.filter(company =>
        company.companyName && company.companyName.toLowerCase().includes(state.searchQuery.toLowerCase())
      );
    },
  },
  
  
  mutations: {
    setCompanies(state, companies) {
        state.companies = companies.map(company => ({
          ...company,
          companyName: company.company_name,
          companyTypes: company.company_types,
          companyPhone: company.company_phone,
          companyEmail: company.company_email,
        })) || [];
      },
      setCompanyDetails(state, companyDetails) {
        state.companyDetails = companyDetails
      },
    setComments(state, comments) {
      state.comments = comments || [];
    },
    setLoading(state, loading) {
      state.loading = loading;
    },
    setSearchQuery(state, query) {
      state.searchQuery = query;
    },
  },
  


  actions: {
    // 通用 AJAX 请求函数
    ajaxRequest({ commit }, { url, type, data, headers, successCallback, errorCallback }) {
      commit("setLoading", true);
      $.ajax({
        url,
        type,
        headers,
        data,
        success(resp) {
          commit("setLoading", false);
          successCallback(resp); // 直接使用响应数据
        },        
        error(err) {
          commit("setLoading", false);
          console.error("AJAX request failed:", err);
          if (errorCallback) errorCallback(err);
        },
      });
    },

   // 加载公司详情
loadCompanyDetails({ commit, rootState, dispatch }, companyId) {
  dispatch("ajaxRequest", {
    url: `http://127.0.0.1:3000/company/detail/${companyId}`,  // 使用路径参数
    type: "get",
    headers: {
      Authorization: "Bearer " + rootState.user.token,
    },
    successCallback(data) {
      console.log(data);
      commit("setCompanyDetails", data);
    },
    errorCallback(err) {
      console.error("Failed to load company details:", err);
    },
  });
},


     // 加载公司列表
     loadCompanies({ commit, rootState }) {
        $.ajax({
          url: "http://127.0.0.1:3000/company/list",  // 假设 API 地址
          type: "get",
          headers: {
            Authorization: "Bearer " + rootState.user.token,
          },
          success(resp) {
            if (resp && Array.isArray(resp)) {
              commit("setCompanies", resp);  // 更新公司列表
            } else {
              console.error("获取公司列表失败");
            }
          },
          error(err) {
            console.error("AJAX request failed:", err);
          },
        });
      },

     // 加载评论
  loadComments({ commit, rootState, dispatch },companyId) {
    dispatch("ajaxRequest", {
      url: "http://127.0.0.1:3000/comment/list",
      type: "get",
      data: { companyId },
      headers: {
        Authorization: "Bearer " + rootState.user.token,
      },
      successCallback(data) {
        commit("setComments", data);
      },
      errorCallback(err) {
        console.error("Failed to load comments:", err);
      },
    });
  },

  // 添加评论
  addComment({ dispatch, rootState }, { companyId, content , rating}) {
    const userId = rootState.user.id; 
    const username = rootState.user.username;
    dispatch("ajaxRequest", {
      url: "http://127.0.0.1:3000/comment/add",
      type: "post",
      data: { userId, companyId ,content ,username, rating},
      headers: {
        Authorization: "Bearer " + rootState.user.token,
      },
      successCallback() {
        dispatch("loadComments", companyId); // 成功后重新加载评论
      },
      errorCallback(err) {
        console.error("Failed to add comment:", err);
      },
    });
  },
  },
};
