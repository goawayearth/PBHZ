<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.tml.service.UserInform" %>
<%@ page import="com.tml.service.impl.UserInfoImpl" %>
<%@ page import="com.tml.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>个人主页</title>
  <link rel="stylesheet" href="./css/reply.css">

</head>

<body>

<div class="container " id="container">
  <div class="main-content person" id="main-content">

    <div class="welcomezi">
      <img src="./images/切图/a60326f2b9cef2962648d1d92ad0dda.png" alt="">
    </div>

    <%
      session = request.getSession();
      String loggedInUserName = (String) session.getAttribute("username");
      UserInfoImpl userInfo = new UserInfoImpl();
      User user = userInfo.getUser(loggedInUserName);
      String qid = user.getUsername();
      System.out.println(loggedInUserName);
    %>

    <form action="/Pbhz/postServlet" method="post" enctype="multipart/form-data" class="input-form">
      <textarea name="replyContent" rows="14" cols="130" placeholder="在此输入问题"></textarea>
      <input type='hidden' name='qid' value="<%= qid %>">
      <label for="category" class="type-lable">选择类别:</label>
      <select name="category" id="category" class="select-type">
        <option value="option1">learn</option>
        <option value="option2">law</option>
        <option value="option3">job</option>
        <!-- 添加更多的选项 -->
      </select>
      <input type="file" name="fileUpload"> <!-- 文件上传字段 -->
      <input type="submit" value="发表">
    </form>

  </div>

</div>

<script>
  function uploadFile() {
    console.log("执行了传输文件的函数");
    var fileInput = document.getElementById('fileUpload');

    var file = fileInput.files[0];
    console.log(file);
    if (file) {
      console.log("发送了")
      var formData = new FormData();
      formData.append('fileUpload', file);
      var xhr = new XMLHttpRequest();
      xhr.open('POST', '/Pbhz/changeIcon', true);
      xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
          console.log(xhr.responseText);
          location.reload();
        }
      };
      xhr.send(formData);
    }
  }
</script>
</body>
</html>
