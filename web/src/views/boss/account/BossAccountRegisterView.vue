<template>
  <div class="container mt-5">
    <h2 class="text-center mb-4">招聘官注册</h2>

    <!-- 步骤进度条 -->
    <div class="d-flex align-items-center justify-content-between mb-4">
      <div :class="['step-indicator', step === 1 ? 'active' : '']">
        <span class="step-number">1</span>
        <span class="step-title">完善个人信息</span>
      </div>
      <div class="step-line"></div>
      <div :class="['step-indicator', step === 2 ? 'active' : '']">
        <span class="step-number">2</span>
        <span class="step-title">填写公司信息</span>
      </div>
    </div>

    <!-- 表单部分 -->
    <form @submit.prevent="handleSubmit">
      <!-- 第一步：个人信息 -->
      <div v-if="step === 1">
        <div class="form-group mb-3">
          <label for="username">用户名</label>
          <input
            type="text"
            id="username"
            v-model="form.username"
            class="form-control"
            placeholder="请输入用户名"
            required
          />
        </div>
        <div class="form-group mb-3">
          <label for="password">密码</label>
          <input
            type="password"
            id="password"
            v-model="form.password"
            class="form-control"
            placeholder="请输入密码"
            required
          />
        </div>
        <div class="form-group mb-3">
          <label for="real_name">真实姓名</label>
          <input
            type="text"
            id="real_name"
            v-model="form.real_name"
            class="form-control"
            placeholder="请输入真实姓名"
            required
          />
        </div>
        <div class="form-group mb-3">
          <label for="gender">性别</label>
          <select id="gender" v-model="form.gender" class="form-control" required>
            <option value="" disabled>请选择性别</option>
            <option value="男">男</option>
            <option value="女">女</option>
          </select>
        </div>
        <div class="form-group mb-3">
          <label for="phone">电话号码</label>
          <input
            type="text"
            id="phone"
            v-model="form.phone"
            class="form-control"
            placeholder="请输入电话号码"
            required
          />
        </div>
        <div class="form-group mb-3">
          <label for="email">邮箱</label>
          <input
            type="email"
            id="email"
            v-model="form.email"
            class="form-control"
            placeholder="请输入邮箱"
            required
          />
        </div>
        <button type="button" class="btn btn-primary w-100" @click="nextStep">下一步</button>
      </div>

      <!-- 第二步：公司信息 -->
      <div v-else-if="step === 2">
        <div class="form-group mb-3">
          <label for="company_name">企业名称</label>
          <input
            type="text"
            id="company_name"
            v-model="form.company_name"
            class="form-control"
            placeholder="请输入企业名称"
            required
          />
        </div>
        <div class="form-group mb-3">
          <label for="company_types">企业类型</label>
          <input
            type="text"
            id="company_types"
            v-model="form.company_types"
            class="form-control"
            placeholder="请输入企业类型"
            required
          />
        </div>
        <div class="form-group mb-3">
          <label for="company_email">企业邮箱</label>
          <input
            type="email"
            id="company_email"
            v-model="form.company_email"
            class="form-control"
            placeholder="请输入企业邮箱"
            required
          />
        </div>
        <div class="form-group mb-3">
          <label for="company_phone">企业电话</label>
          <input
            type="text"
            id="company_phone"
            v-model="form.company_phone"
            class="form-control"
            placeholder="请输入企业电话"
            required
          />
        </div>
        <div class="form-group mb-3">
          <label for="company_content">企业介绍</label>
          <textarea
            id="company_content"
            v-model="form.company_content"
            class="form-control"
            rows="3"
            placeholder="请输入企业介绍"
          ></textarea>
        </div>
        <div class="form-group mb-3">
          <label for="company_create_time">企业成立时间</label>
          <input
            type="date"
            id="company_create_time"
            v-model="form.company_create_time"
            class="form-control"
            required
          />
        </div>
        
        <div class="d-flex">
          <button type="button" class="btn btn-secondary w-50 me-2" @click="prevStep">
            返回上一步
          </button>
          <button type="submit" class="btn btn-success w-50">
            提交注册
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import $ from 'jquery'
export default {
  data() {
    return {
      step: 1, // 当前步骤
      form: {
        username: '',
        password: '',
        real_name: '',
        gender: '',
        phone: '',
        email: '',
        company_name: '',
        company_types: '',
        company_email: '',
        company_phone: '',
        company_content: '',
        company_create_time: '',
      },
      error_message: '', // 错误信息
    };
  },
  methods: {
    nextStep() {
      if (this.validatePersonalInfo()) {
        this.step = 2; // 进入第二步
      } else {
        alert('请填写完整的个人信息！');
      }
    },
    prevStep() {
      this.step = 1; // 返回第一步
    },
    validatePersonalInfo() {
      const { username, password, real_name, gender, phone, email } = this.form;
      return username && password && real_name && gender && phone && email;
    },
    handleSubmit() {
      const { username, password, real_name, gender, phone, email, company_name, company_types, company_email, company_phone, company_content, company_create_time} = this.form;

      // 使用 jQuery AJAX 发送请求
      $.ajax({
        url: "http://127.0.0.1:3000/api/recruiters/register", // 确保路径正确
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
          username,
          password,
          real_name,
          gender,
          phone,
          email,
          company_name,
          company_types,
          company_email,
          company_phone,
          company_content,
          company_create_time,
        }),
        success: (resp) => {
          if (resp.error_message === "success") {
            alert('注册成功！即将跳转到登录页面...');
            this.$router.push({ name: 'user_account_login' }); // 跳转到登录页面
          } else {
            this.error_message = resp.error_message; // 显示后端返回的错误信息
          }
        },
        error: (xhr) => {
          console.error("提交失败:", xhr);
          this.error_message = "提交失败，请稍后再试！";
        },
      });
    },
  },
};
</script>


<style scoped>
.container {
  max-width: 500px;
}
.step-indicator {
  display: flex;
  align-items: center;
  flex-direction: column;
  text-align: center;
}
.step-indicator .step-number {
  width: 30px;
  height: 30px;
  line-height: 30px;
  border: 2px solid #ccc;
  border-radius: 50%;
  font-weight: bold;
  background-color: #fff;
}
.step-indicator.active .step-number {
  border-color: #28a745;
  background-color: #28a745;
  color: #fff;
}
.step-title {
  margin-top: 5px;
  font-size: 14px;
}
.step-line {
  flex-grow: 1;
  height: 2px;
  background-color: #ccc;
  margin: 0 10px;
}
</style>
