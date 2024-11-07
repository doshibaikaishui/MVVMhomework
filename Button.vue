<script setup>
import { ref } from 'vue'

const title = ref('初始标题')
const currentTime = ref('')

function changeTitleAndShowTime() {
        // 改变 <h1> 元素的内容
        title.value = '欢迎来到我的网站';
        
        const nowTime = new Date();
        const nowDate = `${nowTime.getFullYear()}/${nowTime.getMonth() + 1}/${nowTime.getDate()} ${nowTime.getHours()}:${nowTime.getMinutes()}:${nowTime.getSeconds()}`;
        
        // 显示当前时间
        currentTime.value = nowDate;
    }


</script>

<template>
    <h1>{{title}}</h1>
    <button @click="changeTitleAndShowTime">改变标题</button>
    <div>{{currentTime}}</div>
</template>
