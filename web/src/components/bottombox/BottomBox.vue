<template>
    <footer class="bg-dark text-light pt-4 pb-4" >
      <div class="container">
        <div class="row">
          <!-- 第一列：企业服务 -->
          <div class="col-md-4 mb-3">
            <h5>企业服务</h5>
            <ul class="list-unstyled">
              <li>
                <a href="#" class="text-secondary text-decoration-none">
                  <i class="bi bi-briefcase me-2"></i>职位搜索
                </a>
              </li>
              <li>
                <a href="#" class="text-secondary text-decoration-none">
                  <i class="bi bi-newspaper me-2"></i>新闻资讯
                </a>
              </li>
              <li>
                <a href="#" class="text-secondary text-decoration-none">
                  <i class="bi bi-phone me-2"></i>职荐无忧APP
                </a>
              </li>
            </ul>
          </div>
          <!-- 第二列：使用与帮助 -->
          <div class="col-md-4 mb-3">
            <h5>使用与帮助</h5>
            <ul class="list-unstyled">
              <li>
                <a href="#" class="text-secondary text-decoration-none">
                  <i class="bi bi-shield-lock me-2"></i>隐私政策
                </a>
              </li>
              <li>
                <a href="#" class="text-secondary text-decoration-none">
                  <i class="bi bi-exclamation-triangle me-2"></i>防骗指南
                </a>
              </li>
              <li>
                <a href="#" class="text-secondary text-decoration-none">
                  <i class="bi bi-question-circle me-2"></i>使用帮助
                </a>
              </li>
            </ul>
          </div>
          <!-- 第三列：联系我们 -->
          <div class="col-md-4 mb-3">
            <h5>联系我们</h5>
            <p class="small text-secondary">
              <i class="bi bi-telephone me-2"></i>联系电话：400-065-5799
            </p>
            <p class="small text-secondary">
              <i class="bi bi-clock me-2"></i>工作时间：8:00 - 22:00
            </p>
            <div class="d-flex">
              <a href="#" class="me-3 text-secondary">
                <i class="bi bi-wechat text-light fs-4"></i>
              </a>
              <a href="#" class="me-3 text-secondary">
                <i class="bi-github text-light fs-4" role="img" aria-label="GitHub"></i>
              </a>
              <a href="#" class="text-secondary">
                <i class="bi bi-app-indicator text-light fs-4"></i>
              </a>
            </div>
          </div>
        </div>
        <hr class="border-secondary">
        <div class="text-center small text-secondary">
          Copyright © 2024 职荐无忧 | 京ICP备14013441号-5
        </div>
      </div>
    </footer>
  </template>
  
  <script>
  export default {};
  </script>
  
  <style scoped>
  footer {
    margin-top: 60px;
    text-align: center;
  }

  footer h5 {
    font-size: 18px;
    font-weight: bold;
  }
  footer a:hover {
    color: #fff; /* 鼠标悬停时高亮 */
  }
  footer i {
    font-size: 20px; /* 图标大小 */
  }
  </style>
  