<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.tml.service.UserInform" %>
<%@ page import="com.tml.service.impl.UserInfoImpl" %>
<%@ page import="com.tml.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>发表</title>
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
      String uid = user.getUsername();
      System.out.println(loggedInUserName);
    %>

<%--    <form action="/Pbhz/postServlet" method="post" enctype="multipart/form-data" class="input-form">--%>
<%--        <textarea name="Content" rows="14" cols="130" placeholder="在此输入问题"></textarea>--%>
<%--      <input type='hidden' name='uid' value="<%= uid %>">--%>
<%--      <label for="category" class="type-lable">选择类别:</label>--%>
<%--      <select name="category" id="category" class="select-type">--%>
<%--        <option value="learning">learning</option>--%>
<%--        <option value="law">law</option>--%>
<%--        <option value="job">job</option>--%>
<%--        <!-- 添加更多的选项 -->--%>
<%--      </select>--%>
<%--      <input type="file" name="fileUpload"> <!-- 文件上传字段 -->--%>
<%--      <input type="submit" value="发表">--%>
<%--    </form>--%>

    <!-- Your HTML Form -->
    <form id="postForm" enctype="multipart/form-data" class="input-form">
      <textarea id="content" name="Content" rows="14" cols="130" placeholder="在此输入问题"></textarea>
      <input type="hidden" id="uid" name="uid" value="<%= uid %>">
      <label for="category" class="type-lable">选择类别:</label>
      <select id="category" name="category" class="select-type">
        <option value="learning">learning</option>
        <option value="law">law</option>
        <option value="job">job</option>
        <option value="psychogical">psychogical</option>
        <option value="help">help</option>
        <option value="other">other</option>

        <!-- 添加更多的选项 -->
      </select>
      <input type="file" id="fileUpload" name="fileUpload"> <!-- 文件上传字段 -->
      <input type="button" value="发表" onclick="submitPostForm()">
    </form>



  </div>

</div>

<script>
  function submitPostForm() {
    // Get form data
    var formData = new FormData();
    formData.append('Content', document.getElementById('content').value);
    formData.append('uid', document.getElementById('uid').value);
    formData.append('category', document.getElementById('category').value);
    formData.append('fileUpload', document.getElementById('fileUpload').files[0]);

    // Send the data using fetch
    fetch('/Pbhz/postServlet', {
      method: 'POST',
      body: formData
    })
            .then(response => {
              if (!response.ok) {
                throw new Error('Network response was not ok');
              }
              return response.text(); // assuming server returns JSON
            })
            .then(data => {
              // Handle the response data as needed
              console.log(data);

              // Optionally: Update UI or display a success message

              // Clear the form after successful submission
              document.getElementById('content').value = '';
              document.getElementById('uid').value = '';
              document.getElementById('category').value = 'learning'; // Set default category
              document.getElementById('fileUpload').value = '';
            })
            .catch(error => {
              console.error('There was a problem with the fetch operation:', error);
            });
  }
</script>

</body>
</html>
