
 function addItem() {
    const input = document.getElementById('itemInput');
    const inputValue = input.value;

    if (inputValue === '') {
        alert('请输入一个值');
        return;
    }

    const list = document.getElementById('myList');

    // 创建一个新的 li 元素
    const li = document.createElement('li');
    li.textContent = inputValue;

    // 创建编辑按钮
    const editButton = document.createElement('button');
    editButton.textContent = '编辑';
    editButton.onclick = function () {
        editItem(li);
    };

    // 创建删除按钮
    const deleteButton = document.createElement('button');
    deleteButton.textContent = '删除';
    deleteButton.onclick = function () {
        deleteItem(li);
    };

    // 添加编辑和删除按钮到 li 中
    li.appendChild(editButton);
    li.appendChild(deleteButton);

    // 将新的 li 添加到 ul 中
    list.appendChild(li);


    input.value = '';
}

// 编辑列表项的函数
function editItem(li) {
    const newValue = prompt('请输入新的内容:', li.firstChild.textContent);
    if (newValue !== null ) {
        li.firstChild.textContent = newValue;
    }
    if(newValue === "") deleteItem(li);
}

// 删除列表项的函数
function deleteItem(li) {
    li.remove();
}

// 为“添加项”按钮添加点击事件
window.onload = function () {
    document.getElementById('addItem').onclick = addItem;
};