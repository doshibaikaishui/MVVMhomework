<template>
    <ContentField>
        <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
            <!-- 主框架 -->
            <div class="card shadow-lg" style="width: 50%; min-width: 600px;background: linear-gradient(rgb(224,242,244), rgb(255,255,255));">
                <!-- 顶部文字描述 -->
                <div class="card-header bg-primary text-white text-center py-3">
                    <h4>欢迎来到直荐无忧</h4>
                    <p class="mb-0">准备开始拿Offer了吗？用户与招聘官可以通过此系统高效交流，寻找工作机会。</p>
                </div>

                <div class="row g-0">
                    <div class="col-md-6 p-4 border-end">
                        <h5 class="text-center mb-4">用户注册</h5>
                        <form @submit.prevent="register">
                            <div class="mb-3 form-floating mb-3">
                                <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                                <label for="username" class="form-label">用户名</label>
                            </div>
                            <div class="mb-3 form-floating mb-3">
                                <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                                <label for="password" class="form-label">密码</label>
                            </div>
                            <div class="mb-3 form-floating mb-3">
                                <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="请再次输入密码">
                                <label for="confirmedPassword" class="form-label">密码</label>
                            </div>
                            <div class="error-message">{{ error_message }}</div>
                            <button type="submit" class="btn btn-success">提交</button>
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
import { ref } from 'vue'
import router from '../../../router/index'
import $ from 'jquery'

export default {
    components: {
        ContentField
    },
    setup() {
        let username = ref('');
        let password = ref('');
        let confirmedPassword = ref('');
        let error_message = ref('');

        const register = () => {
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/register/",
                type: "post",
                data: {
                    username: username.value,
                    password: password.value,
                    confirmedPassword: confirmedPassword.value,
                },
                success(resp) {
                    // 成功直接返回登录界面
                    if (resp.error_message === "success") {
                        router.push({name: "user_account_login"});
                    } else {
                        error_message.value = resp.error_message;
                    }
                },
              });

        }

        return {
            username,
            password,
            confirmedPassword,
            error_message,
            register,
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
    justify-content: center;
}

.bg-primary {
    background-color: white !important;
    background: linear-gradient(45deg,rgb(0,190,189), rgb(0,180,179));
}
</style>

