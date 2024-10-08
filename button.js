window.onload = function() {
    document.getElementById('changeTitle').onclick = function changeTitleAndShowTime() {
        // 改变 <h1> 元素的内容
        document.querySelector('h1').innerHTML = '欢迎来到我的网站';
        
        const nowTime = new Date();
        const nowDate = `${nowTime.getFullYear()}/${nowTime.getMonth() + 1}/${nowTime.getDate()} ${nowTime.getHours()}:${nowTime.getMinutes()}:${nowTime.getSeconds()}`;
        
        // 显示当前时间
        document.getElementById('currentTime').innerHTML = nowDate;
    }
};