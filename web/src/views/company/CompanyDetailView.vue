<template>
  <ContentField style="min-height: 700px;">
    <div class="company-detail card p-4" v-if="company">
      <img
      :src="company.company_photo || defaultPhoto"
      class="rounded-circle mb-3"
      alt="公司头像"
      style="width: 100px; height: 100px; object-fit: cover;"
    />
      <h3 class="card-title">{{ company.company_name }}</h3>
      <p class="card-text">{{ company.company_content }}</p>
      
      <div class="row">
        <div class="col-md-6">
          <p class="card-text"><strong>电话:</strong> {{ company.company_phone }}</p>
          <p class="card-text"><strong>邮箱:</strong> {{ company.company_email }}</p>
        </div>
        <div class="col-md-6">
          <p class="card-text"><strong>成立时间:</strong> {{ company.company_create_time }}</p>
        </div>
      </div>
    </div>
    

    <!-- 留言功能 -->
    <div class="comment-section mt-4">
      <h5>求职者评价</h5>
      <div class="star-rating">
        <span v-for="star in 5" :key="star" @click="setRating(star)" :class="['star', { 'filled': rating >= star }]">&#9733;</span>
      </div>
      <textarea class="form-control mt-2" v-model="newComment" placeholder="写下您的评价"></textarea>
      <button class="btn btn-primary mt-2" @click="postComment">发表评价</button>
      <ul class="list-group mt-3">
        <li v-for="comment in comments" :key="comment.id" class="list-group-item">
          <strong>{{ comment.username }}</strong> ({{ comment.createTime }})
          <div>{{ comment.content }}</div>
          <div class="star-rating">
            <span v-for="star in 5" :key="star" :class="['star', { 'filled': comment.rating >= star }]">&#9733;</span>
          </div>
        </li>
      </ul>
    </div>
  </ContentField>
  <BottomBox></BottomBox>
</template>

<script>
import ContentField from "../../components/ContentField.vue";
import { mapState } from "vuex";
import BottomBox from "@/components/bottombox/BottomBox.vue";

export default {
  props: ["userId"],
  components: {
    ContentField,
    BottomBox,
  },
  data() {
    return {
      newComment: "", // 新留言
      rating: 0, // 当前选择的星级评分
    };
  },
  computed: {
    ...mapState("company", ["companyDetails", "comments"]),
    company() {
      return this.companyDetails;
    },
  },
  methods: {
    // 设置星级评分
    setRating(star) {
      this.rating = star;
    },

    // 发布评论
    postComment() {
      const companyId = this.$route.query.company_id;
      this.$store
        .dispatch("company/addComment", {
          companyId,
          content: this.newComment,
          rating: this.rating,
        })
        .then(() => {
          this.newComment = ""; // 清空输入框
          this.rating = 0; // 重置评分
          this.loadComments(); // 重新加载评论
        })
        .catch((err) => {
          console.error("Failed to post comment:", err);
        });
    },

    // 加载评论
    loadComments() {
      const companyId = this.$route.query.company_id;
      this.$store.dispatch("company/loadComments", companyId);
    },
  },
  mounted() {
    const companyId = this.$route.query.company_id;
    this.$store.dispatch("company/loadCompanyDetails", companyId);
    this.$store.dispatch("company/loadComments", companyId);
  },
};
</script>

<style scoped>
.comment-section {
  margin-top: 20px;
}

textarea {
  width: 100%;
  height: 80px;
  margin-top: 10px;
}

button {
  margin-top: 10px;
}

/* Star Rating */
.star-rating {
  display: flex;
  font-size: 1.5rem;
  color: #ccc;
}

.star {
  cursor: pointer;
  margin-right: 5px;
}

.star.filled {
  color: #ffcc00;
}

.list-group-item {
  margin-bottom: 10px;
}
</style>
