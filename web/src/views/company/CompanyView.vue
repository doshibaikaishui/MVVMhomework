<template>
  <ContentField>
    <!-- 搜索框 -->
    <nav class="navbar">
      <div class="container-fluid d-flex justify-content-center">
        <form class="d-flex justify-content-center w-100" role="search">
          <input
            class="form-control custom-input w-100"
            type="search"
            placeholder="搜索公司"
            v-model="searchQuery"
          />
          <button type="button" class="btn btn-primary" @click="searchCompanies">
            搜索
          </button>
        </form>
      </div>
    </nav>

    <!-- 公司列表 -->
    <div class="company-list" style="min-height:600px;">
      <div
        class="company-item"
        v-for="company in filteredCompanies"
        :key="company.id"
        @click="goToCompanyDetail(company.id)"
      >
        <h5>{{ company.companyName }}</h5>
        <p>{{ company.companyTypes }} | {{ company.companyPhone }} | {{ company.companyEmail }}</p>
      </div>
    </div>
  </ContentField>
  <BottomBox></BottomBox>
</template>

<script>
import ContentField from "../../components/ContentField.vue";
import BottomBox from "@/components/bottombox/BottomBox.vue";
import { mapGetters } from "vuex";

export default {
  components: {
    ContentField,
    BottomBox,
  },
  data() {
    return {
      searchQuery: "", // 搜索关键字
    };
  },
  computed: {
    ...mapGetters("company", ["filteredCompanies"]),
  },
  methods: {
  searchCompanies() {
    // 更新 Vuex 中的 searchQuery
    this.$store.commit('company/setSearchQuery', this.searchQuery);
  },
  goToCompanyDetail(companyId) {
    this.$router.push(`/company/detail?company_id=${companyId}`);
  },
},

  mounted() {
    this.$store.dispatch("company/loadCompanies");
  },
};
</script>

<style scoped>
.company-list {
  margin: 20px;
}
.company-item {
  padding: 15px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
  cursor: pointer;
  border-radius: 8px;
}
.company-item:hover {
  background-color: #f9f9f9;
}

.custom-input {
  width: 900px;
  height: 54px;
  border: 2px solid #00bebd;

  border-bottom-left-radius: 20px;
  border-top-left-radius: 20px;
  border-end-end-radius: 0px;
  border-top-right-radius: 0px;
}
.btn  {
  border-bottom-left-radius: 0px;
  border-top-left-radius: 0px;
  border-end-end-radius: 20px;
  border-top-right-radius: 20px;
}

.btn-primary {
  width: 70px;
  height: 54px;

}
</style>
