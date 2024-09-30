<template>
  <div class="container">
    <!-- 右边栏 -->
    <aside class="sidebar">
      <div class="user-info">
        <h2>{{ displayName }}</h2>
        <p>{{ systemName }}</p>
      </div>
      <ul class="menu">
        <li @click="currentPage = 'budgetControl'">预算控制</li>
        <li @click="currentPage = 'accounting'">记账</li>
        <li @click="currentPage = 'viewRecords'">查看账目流水</li>
        <li @click="currentPage = 'accountStatistics'">账目统计</li>
        <li @click="currentPage = 'monthlyBalance'">本月剩余额度</li>
        <li @click="currentPage = 'systemSettings'">系统设置</li>
      </ul>
    </aside>

    <!-- 左边页面内容 -->
    <main class="main-content">
      <div v-if="currentPage === 'budgetControl'">
        <h2>预算控制</h2>
        <label>每日预算额度: <input v-model="dailyBudget" type="number"></label>
        <br />
        <label>每月预算额度: <input v-model="monthlyBudget" type="number"></label>
      </div>

      <div v-if="currentPage === 'accounting'">
        <h2>记账</h2>
        <label>账目名: <input v-model="newRecord.name" type="text"></label>
        <br />
        <label>账目金额: <input v-model="newRecord.amount" type="number"></label>
        <br />
        <label>账目备注: <input v-model="newRecord.note" type="text"></label>
        <br />
        <button @click="addRecord">添加</button>
      </div>

      <div v-if="currentPage === 'viewRecords'">
        <h2>查看账目流水</h2>
        <label>起始日期: <input v-model="startDate" type="date"></label>
        <br />
        <label>终止日期: <input v-model="endDate" type="date"></label>
        <br />
        <button @click="viewRecords">查看</button>

        <table v-if="filteredRecords.length">
          <thead>
            <tr>
              <th>序列号</th>
              <th>账目名</th>
              <th>账目金额</th>
              <th>账目备注</th>
              <th>账目日期</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(record, index) in filteredRecords" :key="index">
              <td>{{ index + 1 }}</td>
              <td>{{ record.name }}</td>
              <td>{{ record.amount }}</td>
              <td>{{ record.note }}</td>
              <td>{{ record.date }}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <div v-if="currentPage === 'accountStatistics'">
        <h2>账目统计</h2>
        <label>起始日期: <input v-model="startDate" type="date"></label>
        <br />
        <label>终止日期: <input v-model="endDate" type="date"></label>
        <br />
        <button @click="viewStatistics">查看统计</button>
      </div>

      <div v-if="currentPage === 'monthlyBalance'">
        <h2>本月剩余额度</h2>
        <p>本月花销: {{ totalSpending }}</p>
        <p>本月预算: {{ monthlyBudget }}</p>
        <p>剩余额度: {{ remainingBudget }}</p>
      </div>

      <div v-if="currentPage === 'systemSettings'">
        <h2>系统设置</h2>
        <label>使用者姓名: <input v-model="newUserName" type="text"></label>
        <br />
        <label>系统名字: <input v-model="systemName" type="text"></label>
        <br />
        <button @click="updateUserName">设置</button>
        <br />
        <button @click="switchTheme">切换主题</button>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      displayName: "小明",
      newUserName: "",
      systemName: "账目流水记录系统",
      currentPage: 'budgetControl',
      dailyBudget: 0,
      monthlyBudget: 0,
      newRecord: {
        name: '',
        amount: 0,
        note: '',
        date: ''
      },
      records: [],
      startDate: '',
      endDate: '',
      totalSpending: 0,
      themeIndex: 0,
      themes: [
        { backgroundColor: '#f0f8ff', color: 'black' },
        { backgroundColor: '#ffe4e1', color: 'black' },
        { backgroundColor: '#f5deb3', color: 'black' },
        { backgroundColor: '#e6e6fa', color: 'black' }
      ],
    };
  },
  computed: {
    remainingBudget() {
      return this.monthlyBudget - this.totalSpending;
    },
    filteredRecords() {
      return this.records.filter(record => {
        const recordDate = new Date(record.date);
        return recordDate >= new Date(this.startDate) && recordDate <= new Date(this.endDate);
      });
    }
  },
  methods: {
    addRecord() {
      const today = new Date().toISOString().split('T')[0];
      this.newRecord.date = today;
      this.records.push({ ...this.newRecord });
      this.totalSpending += Number(this.newRecord.amount);

      if (this.newRecord.amount > this.dailyBudget) {
        alert("已经超过本日额度！");
      }
      if (this.totalSpending > this.monthlyBudget) {
        alert("已经超过本月额度！");
      }

      this.newRecord = { name: '', amount: 0, note: '', date: '' };
    },

    updateUserName() {
      this.displayName = this.newUserName || "使用者姓名";
    },
    switchTheme() {
      this.themeIndex = (this.themeIndex + 1) % this.themes.length;
      const theme = this.themes[this.themeIndex];
      document.body.style.backgroundColor = theme.backgroundColor;
      document.body.style.color = theme.color;
    }
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row-reverse;
}

.sidebar {
  width: 30%;
  background-color: #f5f5f5;
  padding: 20px;
  border-right: 2px solid #ccc; /* 右边栏边框 */
}

.menu {
  list-style-type: none;
  padding: 0;
}

.menu li {
  padding: 10px;
  cursor: pointer;
}

.main-content {
  width: 70%;
  padding: 20px;
  border-left: 2px solid #ccc; /* 左边栏边框 */
  border-bottom: 2px solid #ccc;
}

h2 {
  border-bottom: 2px solid #ccc; /* 章节标题下边框 */
  padding-bottom: 5px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ccc; /* 表格边框 */
  padding: 8px;
  text-align: left;
}
</style>
