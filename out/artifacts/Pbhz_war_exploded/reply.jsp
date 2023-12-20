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
            <form id="replyForm" enctype="multipart/form-data">
                <textarea id="replyContent" rows="14" cols="130" placeholder="在此输入回复内容"></textarea>
                <input type="hidden" id="qid" value="<%= qid %>">
                <input type="file" id="fileUpload"> <!-- 文件上传字段 -->
                <input type="button" value="回复" onclick="submitForm()">
            </form>

        </div>
        <div id="contain">

        </div>

    </div>
</div>

    <script>

        let qid;
        window.onload = function(){
            let urlSearchParams = new URLSearchParams(window.location.search);
            qid = urlSearchParams.get('id');
            console.log(qid)
            console.log("你好")
            loadComment();

        }

        function loadComment(){
            fetch('http://localhost:8080/Pbhz/homeLoadServlet?action=getComment&qid='+qid)
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    let html = "";
                    //处理返回的内容
                    for(let d of data){
                        let single = "<div class='use'><div class='icon-person'><img class='icon-image1' src='"+d.icon+"' alt='faild'></div>"+
                            "<div class='name'>" + d.name + " :</div></div>" +
                            "<div class='cont'>" + d.content + "</div>";

                        if(d.filename){
                            single += "<div><img class='commentImage' src='" + d.filename + "' alt='Uploaded Image'></div>"
                        }

                        single += ("<div><span class='date d1'>" + d.date + "</span> </div>"+"<br><hr class='hr'>")

                        html+=single;
                    }

                    document.getElementById("contain").innerHTML = html;

                })
                .catch(error => console.error('error:',error));
        }

        function submitForm() {
            console.log("提交了");
            // Get form data
            var formData = new FormData();
            formData.append('replyContent', document.getElementById('replyContent').value);
            formData.append('qid', document.getElementById('qid').value);
            if(document.getElementById('fileUpload').files[0])
                formData.append('fileUpload', document.getElementById('fileUpload').files[0]);

            // Send the data using fetch
            fetch('/Pbhz/addReplyServlet', {
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
                    console.log("data:"+data);
                    document.getElementById("replyContent").value = '';
                    document.getElementById('fileUpload').value = '';

                    loadComment();
                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
        }


    </script>

</body>
</html>
