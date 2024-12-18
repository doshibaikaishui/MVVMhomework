import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/home/HomeView.vue'
import JobView from '../views/job/JobView.vue'
import NotFound from '@/views/error/NotFound.vue'
import UserAccountLoginView from '../views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterView from '../views/user/account/UserAccountRegisterView.vue'
import store from '../store/index'
import UserCenterView from '@/views/user/UserCenterView.vue'
import RemuseView from '@/views/user/RemuseView.vue'
import ApplyView from '@/views/user/ApplyView.vue'
import OfferView from '@/views/user/OfferView.vue'
import BossAccountRegisterView from '@/views/boss/account/BossAccountRegisterView.vue'
import JobManagementView from '@/views/boss/JobManagementView.vue'
import InterviewView from '@/views/boss/InterviewView.vue'
import ResumeManagementView from '@/views/boss/ResumeManagementView.vue'
import JobDetailView from '@/views/job/JobDetailView.vue'
import ResumeCreateView from '@/views/user/ResumeCreateView.vue'
import CompanyView from '../views/company/CompanyView.vue'
import CompanyDetailView from '@/views/company/CompanyDetailView.vue'
import JobCreateView from '@/views/boss/JobCreateView.vue'
import UserResume from '@/views/user/UserResume.vue'

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/home/",
    meta: {
      requestAuth: true,
    }
    
  },
  {
    path: "/home/",
    name: "home_index",
    component: HomeView,
    meta: {
      requestAuth: true,
    }
    
  },
  {
    path: "/company/",
    name: "company_index",
    component: CompanyView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/job/",
    name: "job_index",
    component: JobView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: '/job/detail', // 职位详情页面
    name: 'JobDetail',
    component: JobDetailView,
    props: route => ({ jobId: route.query.job_id }), // 将查询参数传递给 JobDetail 组件
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/company/",
    name: "company_index",
    component: CompanyView,
    meta: {
      requestAuth: true,
    },
  },
  {
    path: '/company/detail',
    name: 'CompanyDetail',
    component: CompanyDetailView,
    props: route => ({ companyId: route.query.company_id }),
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/"
  },
  {
    path: "/user/account/login",
    name: "user_account_login",
    component: UserAccountLoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/user/account/register",
    name: "user_account_register",
    component: UserAccountRegisterView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/user/center/",
    name: "user_center",
    component: UserCenterView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/resume/",
    name: "RemuseView",
    component: RemuseView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/resume/detail/",
    name: "resume_detail",
    component: UserResume,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/apply/",
    name: "ApplyView",
    component: ApplyView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/Offer/",
    name: "OfferView",
    component: OfferView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/boss/register/",
    name: "bossregister",
    component: BossAccountRegisterView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/boss/center/",
    name: "boss_center",
    component: JobManagementView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/boss/interview/",
    name: "boss_interview",
    component: InterviewView,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/boss/resume/",
    name: "boss_resume",
    component: ResumeManagementView,
    meta: {
      requestAuth: true,
    }
  },
  {
      path: "/resume/create/",
      name: "resume_create",
      component: ResumeCreateView,
      meta: {
        requestAuth: true,
      }
  },
  {
    path: "/boss/resume/create/",
    name: "boss_resume_create",
    component: JobCreateView,
    meta: {
      requestAuth: true,
    }
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({name: "user_account_login"});
  } else {
    next();
  }
})

export default router
