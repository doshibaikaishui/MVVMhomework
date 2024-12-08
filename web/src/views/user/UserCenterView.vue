<template>
  <div class="container mt-4">
    <h2 class="text-center text-primary">USER / CENTER</h2>
    <h5 class="text-center text-muted mb-4">个人中心</h5>

    <div v-if="alertMessage" class="alert alert-success alert-dismissible fade show mt-3" role="alert">
      {{ alertMessage }}
      <button type="button" class="btn-close" @click="closeAlert"></button>
    </div>

    <div class="row">
      <LeftNav></LeftNav>

      <!-- 右侧表单 -->
      <div class="col-9">
        <form @submit.prevent="updateUserInfo">
          <div class="mb-3">
            <label for="username" class="form-label">账户</label>
            <input
              type="text"
              class="form-control"
              id="username"
              :value="user.username"
              disabled
            />
          </div>
          <div class="mb-3">
            <label for="real_name" class="form-label">真实姓名</label>
            <input
              type="text"
              class="form-control"
              id="real_name"
              v-model="editableUser.real_name"
            />
          </div>
          <div class="mb-3 text-center">
            <img
              :src="user.photo || defaultPhoto"
              class="rounded-circle mb-3"
              alt="用户头像"
              style="width: 100px; height: 100px; object-fit: cover;"
            />
            <div>
              <button type="button" class="btn btn-secondary btn-sm" @click="uploadPhoto">
                上传头像
              </button>
            </div>
          </div>
          <div class="mb-3">
            <label for="phone" class="form-label">手机号</label>
            <input
              type="text"
              class="form-control"
              id="phone"
              v-model="editableUser.phone"
            />
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">邮箱</label>
            <input
              type="email"
              class="form-control"
              id="email"
              v-model="editableUser.email"
            />
          </div>
          <div class="mb-3">
            <label for="gender" class="form-label">性别</label>
            <select
              class="form-select"
              id="gender"
              v-model="editableUser.gender"
            >
              <option value="male">男</option>
              <option value="female">女</option>
              <option value="other">其他</option>
            </select>
          </div>
          <button type="submit" class="btn btn-primary">保存修改</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed, ref, onMounted } from "vue";
import LeftNav from '../../components/usercenter/LeftNav.vue'

export default {
  components: {
    LeftNav,
  },
  setup() {
    const store = useStore();
    const defaultPhoto = require("../../assets/logo.png");

    const user = computed(() => store.state.user);
    const editableUser = ref({});
    const alertMessage = ref("");

    const showAlert = (message) => {
      alertMessage.value = message;
      setTimeout(() => {
        alertMessage.value = "";
      }, 3000);
    };

    const closeAlert = () => {
      alertMessage.value = "";
    };

    onMounted(() => {
      if (!store.state.is_login) {
        store.dispatch("getinfo", {
          success(resp) {
            editableUser.value = {
              username: resp.username,
              real_name: resp.real_name,
              email: resp.email,
              phone: resp.phone,
              gender: resp.gender,
            };
          },
          error() {
            showAlert("加载中，点击其他页面刷新");
          },
        });
      } else {
        editableUser.value = {
          username: user.value.username,
          real_name: user.value.real_name,
          email: user.value.email,
          phone: user.value.phone,
          gender: user.value.gender,
        };
      }
    });

    const updateUserInfo = () => {
      store.dispatch("updateUserInfo", {
        ...editableUser.value,
        success(resp) {
          showAlert(resp.message);
        },
        error(resp) {
          showAlert(resp.message);
        },
      });
    };

    const uploadPhoto = () => {
      showAlert("上传头像功能暂未实现");
    };

    return {
      user,
      editableUser,
      defaultPhoto,
      alertMessage,
      showAlert,
      closeAlert,
      updateUserInfo,
      uploadPhoto,
    };
  },
};
</script>

<style scoped>

</style>
