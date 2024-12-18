<template>
    <div class="container mt-4">
        <h2 class="text-center text-primary">BOSS / CENTER</h2>
        <h5 class="text-center text-muted mb-4">职位管理</h5>

        <div class="row">
        <LeftNav></LeftNav>
    <div class="col-9" style="min-height: 600px;">
      <div class="chat-container">
        <h3>面试聊天</h3>
        <div class="chat-box">
          <div v-for="msg in messages" :key="msg.id" class="chat-message">
            <strong>{{ msg.sender }}:</strong> {{ msg.message }}
          </div>
        </div>
        <input type="text" v-model="newMessage" placeholder="请输入消息" />
        <button @click="sendMessage">发送</button>
      </div>
    </div>
</div>
    </div>
    <BottomBox></BottomBox>
  </template>
  
  <script>
  import $ from 'jquery'
  import LeftNav from '../../components/bosscenter/LeftNav.vue'
  import BottomBox from '@/components/bottombox/BottomBox.vue';

  export default {
    components: {
        LeftNav,
        BottomBox
    },
    data() {
      return {
        resumeId: null,
        messages: [],
        newMessage: ""
      };
    },
    methods: {
      loadMessages() {
        $.ajax({
          url: `http://127.0.0.1:3000/interview/messages/${this.resumeId}`,
          type: 'GET',
          success: (res) => {
            this.messages = res;
          }
        });
      },
      sendMessage() {
        if (this.newMessage.trim() === "") return;
        const message = {
          resumeId: this.resumeId,
          sender: "招聘官",
          message: this.newMessage
        };
        $.ajax({
          url: "http://127.0.0.1:3000/interview/send",
          type: "POST",
          contentType: "application/json",
          data: JSON.stringify(message),
          success: () => {
            this.messages.push(message);
            this.newMessage = "";
          }
        });
      }
    },
    mounted() {
      this.resumeId = this.$route.params.resumeId; // 假设从路由传递简历ID
      this.loadMessages();
    }
  };
  </script>
  
  <style>
  .chat-box {
    border: 1px solid #ddd;
    height: 300px;
    overflow-y: scroll;
    padding: 10px;
    margin-bottom: 10px;
  }
  .chat-message {
    margin-bottom: 5px;
  }
  </style>
  