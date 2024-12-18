<template>
    <ContentField v-if="!$store.state.user.pulling_info">
        <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
            <!-- 主框架 -->
            <div class="card shadow-lg" style="width: 50%; min-width: 600px;background: linear-gradient(rgb(224,242,244), rgb(255,255,255));">
                <!-- 顶部文字描述 -->
                <div class="card-header bg-primary text-white text-center py-3">
                    <h4>欢迎来到直荐无忧</h4>
                    <p class="mb-0">用户与招聘官可以通过此系统高效交流，共享工作机会。</p>
                </div>

                <div class="row g-0">
                    <!-- 用户登录部分 -->
                    <div class="col-md-6 p-4 border-end">
                        <h5 class="text-center mb-4">用户登录</h5>
                        <form @submit.prevent="login">
                            <div class="form-floating mb-3">
                                <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                                <label for="username " class="form-label ">用户名</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                                <label for="password " class="form-label">密码</label>
                            </div>
                            <div class="error-message text-danger">{{ error_message }}</div>
                            <button type="submit" class="btn btn-success w-100 mt-3">提交</button>
                        </form>
                    </div>

                    <!-- 招聘官登录/注册部分 -->
                    <div class="col-md-6 p-4 text-center">
                        <h5 class="mb-4">招聘官入口</h5>
                        <router-link  class="btn btn-outline-secondary w-75" :to="{name: 'bossregister'}">招聘官注册</router-link>
                    </div>
                </div>
            </div>
        </div>
    </ContentField>
</template>

<script>
import ContentField from '../../../components/ContentField.vue'
import { useStore } from 'vuex'
import { ref } from 'vue'
import router from '../../../router'

export default {
    components: {
        ContentField
    },
    setup() {
        const store = useStore();
        let username = ref('');
        let password = ref('');
        let error_message = ref('');


        const jwt_token = localStorage.getItem("jwt_token");
        if (jwt_token) {
            store.commit("updateToken", jwt_token);
            store.dispatch("getinfo", {
                success() {
                    router.push({ name: "home" });
                    store.commit("updatePullingInfo", false);
                },
                error() {
                    store.commit("updatePullingInfo", false);
                }
            })
        }else {
            store.commit("updatePullingInfo", false);
        }

        const login = () => {
            error_message.value = "";
            store.dispatch("login", {
                username: username.value,
                password: password.value,
                success() {
                    store.dispatch("getinfo", {
                        success() {
                            router.push({ name: 'home' });
                            console.log(store.state.user);
                        }
                    })
                },
                error() {
                    error_message.value = "用户名或密码错误";
                }
            })
        }
        return {
            username,
            password,
            error_message,
            login,
        }
    }
}
</script>


<style scoped>
button {
    width: 100%;
}
div.error-message {
    color: red;
}

.bg-primary {
    background-color: white !important;
    background: linear-gradient(45deg,rgb(0,190,189), rgb(0,180,179));
}
</style>
