function addRowAndSort() {
    // 输入姓名和年龄
    const name = document.getElementById('nameInput').value;
    const age = parseInt(document.getElementById('ageInput').value);

    // 如果姓名不为空且年龄为数字
    if (name && !isNaN(age) && age>=0) {
      const table = document.getElementById('myTable');
      const row = table.insertRow(-1);// 在表格最后插入新行
      const cell1 = row.insertCell(0);
      const cell2 = row.insertCell(1);
      const cell3 = row.insertCell(2);// 分别创建姓名，年龄，按钮三个单元格

      // 将输入的姓名，年龄与按钮添加到创建的单元格中
      cell1.innerHTML = name;
      cell2.innerHTML = age;
      cell3.innerHTML = `<button onclick="deleteRow(this)">删除</button>`;

      // 按年龄升序排列
      sortTableByAge();

      // 更新表格行号
      updateRowNumbers();
    }
    else
     alert("数据不合法，请重新输入");
  }

  // 按年龄排序
  function sortTableByAge() {
    const table = document.getElementById("myTable");
    const rows = Array.from(table.rows).slice(1); // 跳过标题行获取所有行
    rows.sort((a, b) => parseInt(a.cells[1].innerText) - parseInt(b.cells[1].innerText));
    rows.forEach(row => table.appendChild(row));
  }

  // 删除当前行
  function deleteRow(button) {
    const row = button.parentNode.parentNode;
    row.parentNode.removeChild(row);
    updateRowNumbers();
  }

  // 更新表格行号
  function updateRowNumbers() {
    const rows = document.querySelectorAll("#myTable tr");
    for (let i = 1; i < rows.length; i++) {
      rows[i].cells[2].innerHTML = `<button onclick="deleteRow(this)">删除</button>`;
    }
  }

  window.onload = function() {
    document.getElementById("addRow").onclick = addRowAndSort;
  };