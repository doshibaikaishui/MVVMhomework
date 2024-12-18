import $ from 'jquery';

export default {
  namespaced: true,
  state: {
    resume: {},
    is_loading: false, // 是否正在加载简历数据
  },
  getters: {
    getResume(state) {
      return state.resume;
    },
    isLoading(state) {
      return state.is_loading;
    },
  },
  mutations: {
    setResume(state, resume) {
      state.resume = resume;
    },
    setLoading(state, loading) {
      state.is_loading = loading;
    },
  },
  actions: {
    fetchResume({ commit }) {
      commit('setLoading', true);
    
      return new Promise((resolve, reject) => {
        $.ajax({
          url: 'http://127.0.0.1:3000/resume/info/',
          type: 'get',
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('jwt_token'),
          },
          success: (resp) => {
            if (resp) {
              commit('setResume', resp);
              resolve(resp); // 返回数据
            } else {
              reject('Error fetching resume: Resume not found.');
            }
          },
          error: (err) => {
            reject(err); // 错误处理
          },
          complete: () => {
            commit('setLoading', false);
          },
        });
      });
    },
    

    updateResume({ commit }, data) {
      console.log(data);
      commit('setLoading', true);
    
      // 检查是否已有简历
      $.ajax({
        url: 'http://127.0.0.1:3000/resume/info/',
        type: 'get',
        headers: {
          Authorization: 'Bearer ' + localStorage.getItem('jwt_token'),
        },
        success: (resp) => {
          if (resp) {
            // 如果有简历，则执行更新操作
            $.ajax({
              url: 'http://127.0.0.1:3000/resume/update/',
              type: 'put',
              headers: {
                Authorization: 'Bearer ' + localStorage.getItem('jwt_token'), // 使用 token 认证
              },
              contentType: 'application/json',
              data: JSON.stringify(data),
              success: (resp) => {
                if (resp === '更新成功') {
                  commit('setResume', data); // 更新简历数据
                } else {
                  console.error('Error updating resume:', resp);
                }
              },
              error: (err) => {
                console.error('Request failed:', err);
              },
              complete: () => {
                commit('setLoading', false);
              },
            });
          } else {
            // 如果没有简历，提示用户创建简历
            alert("您还没有简历，创建简历后再进行更新！");
            commit('setLoading', false);
          }
        },
        error: (err) => {
          console.error('Request failed:', err);
          commit('setLoading', false);
        },
      });
    },
    createResume({ commit }, resume) {
      
      commit("setResume", resume);

      return new Promise((resolve, reject) => {
        $.ajax({
          url: 'http://127.0.0.1:3000/resume/create/',
          type: 'post',
          headers: {
            Authorization: 'Bearer ' + localStorage.getItem('jwt_token'),
          },
          contentType: 'application/json',
          data: JSON.stringify(resume),
          success: (resp) => {
            if (resp === 'success') {
              resolve({ message: '简历创建成功' });
            } else {
              resolve({ error_message: resp });
            }
          },
          error: () => {
            reject({ error_message: '请求失败' });
          },
        });
      });
    },
    // 修改 fetchUserData 方法
fetchUserData({ commit }, { success, error }) {

  commit('setLoading', true);
  $.ajax({
    url: 'http://127.0.0.1:3000/user/account/info/',
    type: 'get',
    headers: {
      Authorization: 'Bearer ' + localStorage.getItem('jwt_token'),
    },
    success: (resp) => {
      if (resp.error_message === "success") {
        success(resp); // 调用成功回调
      } else {
        error(resp); // 调用失败回调
      }
    },
    error: (err) => {
      error(err); // 发生错误时调用失败回调
    },
    complete: () => {
      commit('setLoading', false);
    },
  });
},

  },
  modules: {},
};
