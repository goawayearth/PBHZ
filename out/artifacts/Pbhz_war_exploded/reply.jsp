<%@ page import="com.tml.service.impl.HomeServiceImpl" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.tml.bean.Question" %>
<%@ page import="com.tml.bean.Comment" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>朋辈互助</title>
    <link rel="stylesheet" href="./css/reply.css">
</head>
<body>
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
                    "<br>"+
                    "<div class='name'>"+question.getName()+" :</div>" +
                    "<div class='cont'>"+question.getContent()+"</div>" +
                    "<div><span class='theme'>#"+question.getType()+"#</span> <span class='date'>"+
                            question.getDate()+"</span> .<span class='num'>"+question.getNum()+
                            "</span><span class='num'>个评论</span></div>"+
                            "<br>"
                );



            %>



        </div>

    </div>

    <div class="container">
        <div class="main-content second-content">
            <div class="comment-title">
                评论留言<hr>

            </div>

            <%
                try {
                    comments = homeService.getCommentsByQid(qid);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                for(int i=0;i<comments.size();i++){
                    out.println(
                                    "<div class='name'>"+ comments.get(i).getName()+" :</div>" +
                                    "<div class='cont'>"+comments.get(i).getContent()+"</div>" +
                                    "<div><span class='date d1'>"+
                                    comments.get(i).getDate()+"</span> </div>"+
                                    "<br><hr class='hr'>"
                    );
                }
            %>

        </div>
    </div>





</body>
</html>
