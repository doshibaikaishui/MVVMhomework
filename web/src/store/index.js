import { createStore } from 'vuex'
import ModuleUser from './user'
import ModuleJob from "./job";
import ModuleResume from "./remuse"
import ModuleCompany from "./company"

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user: ModuleUser,
    job: ModuleJob,
    resume: ModuleResume,
    company: ModuleCompany,
  }
})
