<%@ page import="java.util.List" %>
<%@ page import="com.tml.bean.Comment" %>
<%@ page import="com.tml.service.impl.HomeServiceImpl" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.tml.bean.Question" %>
<%@ page import="java.util.logging.Logger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>朋辈互助</title>
    <link rel="stylesheet" href="./css/reply.css">
</head>
<body>

<!-- 1、顶部问题区域 -->
<div class="container" id="container">
    <div class="main-content" id="main-content">

        <div class="welcomezi">
            <img src="./images/切图/a60326f2b9cef2962648d1d92ad0dda.png" alt="">
        </div>

        <%
            List<Comment> comments = null;
            Question question = null;
            String qid = request.getParameter("id");
            HomeServiceImpl homeService = new HomeServiceImpl();
            try {
                question = homeService.getQuestionByQid(qid);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            out.println(
                    "<br>"+"<div class='use'><div class='icon-person'><img class='icon-image1' src='"+question.getIcon()+"' alt='faild'></div>"+

                            "<div class='name'>"+question.getName()+" :</div></div>" +
                            "<div class='cont'>"+question.getContent()+"</div>" );
            if(question.getFilepath()!=null)
            {
                out.println("<div><img class='commentImage1' src='" + question.getFilepath() + "' alt='Uploaded Image'></div>");
            }

            out.println(    "<div><span class='theme'>#"+question.getType()+"#</span> <span class='date'>发表于"+
                            question.getDate()+"</span></div>"+
                            "<br>"
            );
            System.out.println("时间："+question.getDate());
        %>
    </div>
</div>

<!-- 2、中间回复栏 -->
<div class="container">
    <div class="main-content second-content">
        <div class="comment-title">
            评论留言<hr>
            <!-- 回复表单 -->
            <form action="/Pbhz/addReplyServlet" method="post" enctype="multipart/form-data">
                <textarea name="replyContent" rows="14" cols="130" placeholder="在此输入回复内容"></textarea>
                <input type="hidden" name="qid" value="<%= qid %>">
                <input type="file" name="fileUpload"> <!-- 文件上传字段 -->
                <input type="submit" value="回复">
            </form>

        </div>


        <!-- 下面的所有评论-->
        <%
            try {
                comments = homeService.getCommentsByQid(qid);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < comments.size(); i++) {
                out.println(
                        "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+comments.get(i).getIcon()+"' alt='faild'></div>"+
                        "<div class='name'>" + comments.get(i).getName() + " :</div></div>" +
                        "<div class='cont'>" + comments.get(i).getContent() + "</div>");

                // 如果有文件信息，显示文件信息
                if (comments.get(i).getFileName() != null && !comments.get(i).getFileName().isEmpty()) {
                    // 获取图片文件路径
                    String imagePath = comments.get(i).getFileName();

                    System.out.println("前端路径"+imagePath);

                    // 显示图片
                    out.println("<div><img class='commentImage' src='" + imagePath + "' alt='Uploaded Image'></div>");
                }

                out.println("<div><span class='date d1'>" + comments.get(i).getDate() + "</span> </div>");

                out.println("<br><hr class='hr'>");
            }
        %>



        <!-- 如果回复成功，清空回复栏并刷新页面 -->
        <%
            if (session.getAttribute("replySuccess") != null && (boolean) session.getAttribute("replySuccess")) {
                session.removeAttribute("replySuccess"); // 移除属性，避免每次加载都刷新
        %>
        <script>
            // 清空回复栏
            document.querySelector('textarea[name="replyContent"]').value = '';
            // 刷新页面
            location.reload();
        </script>
        <%
            }
        %>



    </div>
</div>

</body>
</html>
