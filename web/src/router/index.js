import { createRouter, createWebHistory } from 'vue-router'
import CompanyView from '../views/company/CompanyView.vue'
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
import BossAccountLoginView from '@/views/boss/account/BossAccountLoginView.vue'

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
      requestAuth: false,
    }
  },
  {
    path: "/resume/",
    name: "RemuseView",
    component: RemuseView,
  },
  {
    path: "/personal-info",
    name: "PersonalInfo",
    component: UserCenterView, // 复用你的个人信息页面
  },
  {
    path: "/apply/",
    name: "ApplyView",
    component: ApplyView,
  },
  {
    path: "/Offer/",
    name: "OfferView",
    component: OfferView,
  },
  {
    path: "/boss/login/",
    name: "bosslogin",
    component: BossAccountLoginView,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/boss/register/",
    name: "bossregister",
    component: BossAccountRegisterView,
    meta: {
      requestAuth: false,
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({name: "user_account_login"});
  } else {
    next();
  }
})

export default router
