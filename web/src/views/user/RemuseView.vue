<template>
  <div class="container mt-4">
    <!-- 主标题 -->
    <h2 class="text-center text-primary mb-4">用户 / 简历</h2>
    <h5 class="text-center text-muted mb-4">个人简历</h5>

    <!-- 简历部分 -->
    <div class="row">
      <LeftNav></LeftNav>
      <div class="col-9" style="min-height: 600px;">
        <div v-if="resume && Object.keys(resume).length > 0">
          <!-- 个人信息部分 -->
          <div class="resume-header card p-4 mb-4">
            <div class="d-flex align-items-center">
              <img :src="user.photo" alt="头像" id="profile-photo" class="rounded-circle" style="width: 100px; height: 100px; object-fit: cover;">
              <div class="ml-4">
                <h2 id="name">{{ user.real_name }}</h2>
                <p><strong>性别:</strong> <span id="gender">{{ user.gender }}</span> | 
                   <strong>年龄:</strong> <span id="age">{{ user.age }}</span> | 
                   <strong>学历:</strong> <span id="education">{{ user.degree }}</span></p>
                <p><strong>电话:</strong> <span id="phone">{{ user.phone }}</span> | 
                   <strong>邮箱:</strong> <span id="email">{{ user.email }}</span></p>
              </div>
            </div>
          </div>

          <!-- 编辑按钮 -->
          <button v-if="!isEditing" class="btn btn-primary mb-3" @click="enableEditMode">
            编辑简历
          </button>

          <!-- 编辑简历部分 -->
          <div v-if="isEditing">
            <div class="form-group mb-3">
              <textarea v-model="editUser.advantages" class="form-control" placeholder="个人优势" rows="4"></textarea>
            </div>
            <div class="form-group mb-3">
              <textarea v-model="editUser.intentions" class="form-control" placeholder="求职意向" rows="4"></textarea>
            </div>
            <div class="form-group mb-3">
              <textarea v-model="editUser.school" class="form-control" placeholder="教育经历" rows="4"></textarea>
            </div>
            <div class="form-group mb-3">
              <textarea v-model="editUser.experience" class="form-control" placeholder="工作经历" rows="4"></textarea>
            </div>
            <div class="d-flex">
              <button class="btn btn-success mr-2" @click="saveResume">保存更改</button>
              <button class="btn btn-secondary" @click="cancelEdit" style="margin-left: 10px;">取消</button>
            </div>
          </div>

          <!-- 显示简历内容 -->
          <div v-else>
            <div class="resume-section mb-4">
              <h3>个人优势</h3>
              <p>{{ resume.advantages || '无' }}</p>
            </div>
            <div class="resume-section mb-4">
              <h3>求职意向</h3>
              <p>{{ resume.intentions || '无' }}</p>
            </div>
            <div class="resume-section mb-4">
              <h3>教育经历</h3>
              <p>{{ resume.school || '无' }}</p>
            </div>
            <div class="resume-section mb-4">
              <h3>工作经历</h3>
              <p>{{ resume.experience || '无' }}</p>
            </div>
          </div>
        </div>

        <!-- 如果简历为空，显示创建简历按钮 -->
        <div v-else class="text-center">
          <p>您还没有简历，点击下面的按钮创建简历。</p>
          <router-link class="btn btn-primary" :to="{ name: 'resume_create' }">创建简历</router-link>
        </div>
      </div>
    </div>
  </div>

  <BottomBox></BottomBox>
</template>

<script>
import LeftNav from '../../components/usercenter/LeftNav.vue';
import BottomBox from '@/components/bottombox/BottomBox.vue';

export default {
  components: {
    LeftNav,
    BottomBox
  },
  data() {
    return {
      user: {},
      resume: {},
      editUser: {
        advantages: '',
        intentions: '',
        school: '',
        experience: ''
      },
      isEditing: false
    };
  },
  methods: {
    // 获取用户信息
    fetchUserData() {
      this.$store.dispatch('resume/fetchUserData', {
        success: (resp) => {
          this.user = resp;
        },
        error: (err) => {
          console.error("获取用户信息失败:", err);
        }
      });
    },

    // 获取简历信息
    fetchResumeData() {
      this.$store
        .dispatch('resume/fetchResume')
        .then((resp) => {
          this.resume = resp || { advantages: '', intentions: '', school: '', experience: '' }; // 确保赋值
        })
        .catch((err) => {
          console.error("获取简历信息失败:", err);
        });
    },

    // 启用编辑模式
    enableEditMode() {
      this.isEditing = true;
      this.editUser = { ...this.resume }; // 加载简历数据到编辑对象
    },

    // 保存简历
    saveResume() {
      this.$store.dispatch('resume/updateResume', this.editUser)
        .then(() => {
          this.resume = { ...this.editUser };
          alert('简历更新成功');
          this.isEditing = false;
        })
        .catch((err) => {
          console.error("更新简历失败:", err);
        });
    },

    // 取消编辑
    cancelEdit() {
      this.isEditing = false;
      this.editUser = { ...this.resume }; // 恢复原始数据
    }
  },
  created() {
    this.fetchUserData(); // 页面加载时获取用户信息
    this.fetchResumeData(); // 页面加载时获取简历数据
  }
};
</script>

<style scoped>
/* 简历头部 */
.resume-header {
  background-color: #f8f9fa;
  border-radius: 8px;
  display: flex;
  padding: 20px;
}

#profile-photo {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 50%;
}

/* 简历内容部分 */
.resume-section h3 {
  font-size: 1.2rem;
  font-weight: bold;
}

.resume-section p {
  font-size: 1rem;
  color: #555;
}

/* 编辑框 */
textarea.form-control {
  border-radius: 8px;
  resize: none;
}

/* 按钮样式 */
button {
  margin-top: 10px;
}
</style>
