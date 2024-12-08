export default {
    namespaced: true,
    state: {
      resume: {
        jianli_xingming: '',
        jianli_phone: '',
        sex_types: 1,
        geren_text: '',
        jianli_types: '',
        jianli_xinzi: '',
      },
    },
    mutations: {
      SET_RESUME_DATA(state, data) {
        state.resume = data;
      },
    },
    actions: {
      fetchResumeData({ commit }) {
        // 模拟 API 请求
        const mockData = {
          jianli_xingming: '张三',
          jianli_phone: '13522342234',
          sex_types: 1,
          geren_text: '善于沟通，团队协作能力强。',
          jianli_types: '前端开发工程师',
          jianli_xinzi: '8-14K',
        };
        commit('SET_RESUME_DATA', mockData);
      },
    },
  };
  