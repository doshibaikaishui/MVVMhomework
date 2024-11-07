<script setup>
import { ref } from 'vue'

const people = ref([])

const name = ref('')
const age = ref('')

// 添加新行并按年龄排序
function addRowAndSort() {
    // 如果姓名不为空且合法
    if(name.value && !isNaN(age.value) && age.value >= 0){
        // 插入people数组中
        people.value.push({name:name.value,age:parseInt(age.value)})

        // 按年龄升序排列
        people.value.sort((a,b) => a.age-b.age)

        // 清空输入框
        name.value = ''
        age.value = ''
    }else {
        alert("数据不合法，重新输入")
    }
}

function deleteRow(index) {
    people.value.splice(index,1)
}
</script>

<template>
    <!-- 表格数据-->
    <table>
        <tr>
          <th>姓名</th>
          <th>年龄</th>
          <th>操作</th>
        </tr>
        <tbody>
            <tr v-for="(person,index) in people" :key="index">
                <td>{{person.name}}</td>
                <td>{{person.age}}</td>
                <td>
                    <button @click="deleteRow(index)">删除</button>
                </td>
            </tr>
        </tbody>
    </table>

    <!--输入框和按钮-->
    <div>
      <input v-model="name" type="text"  placeholder="姓名" />
      <input v-model="age" type="number" id="ageInput" placeholder="年龄" />
      <button @click="addRowAndSort">添加数据</button>
    </div>
</template>

<style scoped>
table {
    width: 50%;
    margin-top: 20px;
    border: 1px solid #ddd;
  }

  th {
    background-color: #dfeefd;
  }

  th,
  td {
    padding: 8px;
    text-align: left;
  }

  tr:nth-child(even) {
    background-color: #f2f2f2;
  }
  
  button {
    color: black;
    border: none;
    padding: 5px 10px;
    cursor: pointer;
}

button:hover {
    background-color: skyblue;
}

</style>
