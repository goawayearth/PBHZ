<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.tml.service.UserInform" %>
<%@ page import="com.tml.service.impl.UserInfoImpl" %>
<%@ page import="com.tml.bean.User" %>
<%@ page import="com.tml.bean.Question" %>
<%@ page import="java.util.List" %>
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
                System.out.println(loggedInUserName);
            %>
            <%--到了对应的用户名--%>
            <%--接下来查找的是自己发表的问题头像--%>
            <div class="icon-user">
                <% out.println("<img class='icon-image' enctype='multipart/form-data' id='icon-image' src="+user.getIconPath()+" alt='未设置图片'>");
                %>
            </div>
            <form action="/Pbhz/changeIcon" method="post" enctype="multipart/form-data">
                <input class="icon-up" type="file" name="fileUpload"> <!-- 文件上传字段 -->
                <input type="submit" value="确认修改头像">
            </form>
            <% out.println("<div class='user-name'>用户名:"+user.getUsername()+" </div>");
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
        "<div class='name'>"+questionList.get(i).getName()+" :</div>" +
        "<a href='reply.jsp?id="+questionList.get(i).getQid()+"'><div class='cont'>"+questionList.get(i).getContent()+"</div></a>" +
        "<div><span class='theme'>#other#</span> <span class='date'>发表于"+questionList.get(i).getDate()+"</span> </div>"+
        "<br></div>");

        }

    out.println("</div></div>");
    %>

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
