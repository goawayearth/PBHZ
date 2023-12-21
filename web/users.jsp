<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.tml.service.UserInform" %>
<%@ page import="com.tml.service.impl.UserInfoImpl" %>
<%@ page import="com.tml.bean.User" %>
<%@ page import="com.tml.bean.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.swing.plaf.IconUIResource" %>
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
      String loggedInUserName = request.getParameter("username");
      UserInfoImpl userInfo = new UserInfoImpl();
      User user = userInfo.getUser(loggedInUserName);
      System.out.println(loggedInUserName);
    %>
    <%--到了对应的用户名--%>
    <%--接下来查找的是自己发表的问题头像--%>
    <div class="icon-user">
      <% out.println("<img class='icon-image' enctype='multipart/form-data' id='icon-image' src="+user.getIconPath()+" alt='未设置图片'>");
      %>
    </div>
    <%--            <form action="/Pbhz/changeIcon" method="post" enctype="multipart/form-data">--%>
    <%--                <input class="icon-up" type="file" name="fileUpload"> <!-- 文件上传字段 -->--%>
    <%--                <input type="submit" value="确认修改头像">--%>
    <%--            </form>--%>

<%--    <form id="iconForm" enctype="multipart/form-data">--%>
<%--      <input class="icon-up" type="file" id="fileUpload"> <!-- 文件上传字段 -->--%>
<%--      <input type="button" value="确认修改头像" onclick="submitIconForm()">--%>
<%--    </form>--%>

    <% out.println("<div class='user-name'>用户名:"+user.getUsername()+" </div>");
    session = request.getSession();
    String root = (String) session.getAttribute("username");
    if(root.equals("root") && (!loggedInUserName.equals("root"))){
      out.println("<button class='rootButton' onclick='deleteUser(\""+loggedInUserName+"\")'>删除用户 </button>");
    }
      System.out.println("#$^%"+user.getIconPath());  %>

    <div class="my-pub">我的发表 :</div>


  </div>

</div>

<%--    <div class="container " id="container1">--%>

<%--    <h1>这是哪</h1>--%>
<%--    </div>--%>
<%out.println("    <div id=\"otherPage\" class=\"otherPage\"><div class='conta'>");
  List<Question> questionList = userInfo.getQuestion(loggedInUserName);
  System.out.println("大小是："+questionList.size());

  for(int i=0;i<questionList.size();i++){
    out.println("<div class='single-rect'>"+ "<br>"+
            "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+questionList.get(i).getIcon()+"' alt='faild'></div>"+
            "<div class='name'>"+questionList.get(i).getName()+" :</div></div>" +
            "<a href='reply.jsp?id="+questionList.get(i).getQid()+"'><div class='cont'>"+questionList.get(i).getContent()+"</div></a>" +
            "<div class='gekai'><span class='theme'>#"+questionList.get(i).getType()+"#</span> <span class='date'>发表于"+questionList.get(i).getDate()+"</span> </div>"+
            "<br></div>");

  }

  out.println("</div></div>");
%>

<script>
  function deleteUser(username){
    fetch('/Pbhz/manageServlet?action=deleteUser&username='+username)
            .then(response => {
              if (!response.ok) {
                throw new Error('Network response was not ok');
              }
              return response.text(); // assuming server returns JSON
            })
            .then(data => {
              // Handle the response data as needed
              console.log("data:"+data);
              // window.history.back();
              // location.reload(true);
              window.location.replace(document.referrer);

            })
            .catch(error => {
              console.error('There was a problem with the fetch operation:', error);
            });
  }

</script>
</body>
</html>
