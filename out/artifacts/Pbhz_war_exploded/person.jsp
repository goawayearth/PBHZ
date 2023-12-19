<%@ page import="jakarta.servlet.http.HttpSession" %>
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
                System.out.println(loggedInUserName);
            %>
            <%--到了对应的用户名--%>
            <%--接下来查找的是自己发表的问题头像--%>
            <div class="icon-user">

            </div>



        </div>

    </div>


</body>
</html>
