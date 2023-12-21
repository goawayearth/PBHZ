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

                            "<div class='name'><a href='users.jsp?username="+question.getName()+"'>"+question.getName()+" :</a></div></div>" +
                            "<div class='cont'>"+question.getContent()+"</div>" );
            if(question.getFilepath()!=null)
            {
                out.println("<div><img class='commentImage1' src='" + question.getFilepath() + "' alt='Uploaded Image'></div>");
            }

            out.println(    "<div><span class='theme'>#"+question.getType()+"#</span> <span class='date'>发表于"+
                            question.getDate()+"</span>");

            session = request.getSession();
            String root = (String) session.getAttribute("username");
            if ("root".equals(root) && (question.getName() != null && !question.getName().equals("root")) || root.equals(question.getName())) {

                out.println("<button style='margin-left: 10px ' onclick='deleteQuestion(\""+question.getQid()+"\")'>删除 </button>");
            }


            out.println("</div><br>");
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
        let user;
        window.onload = function(){


            let urlSearchParams = new URLSearchParams(window.location.search);
            qid = urlSearchParams.get('id');
            console.log(qid)
            console.log("你好")


            fetch('http://localhost:8080/Pbhz/detectRoot')
                .then(response => response.text())
                .then(data => {
                    data1 = data;
                    loadComment();
                })
                .catch(error => console.error('error:',error));




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
                            "<div class='name'><a href='users.jsp?username="+d.name+"'>" + d.name + " :</a></div></div>" +
                            "<div class='cont'>" + d.content + "</div>";

                        if(d.filename){
                            single += "<div><img class='commentImage' src='" + d.filename + "' alt='Uploaded Image'></div>"
                        }

                        single += ("<div><span class='date d1'>" + d.date + "</span> ");
                        if (data1 == 'root' || data1 == d.name) {
                            let cid = d.cid;
                            single += "<button id='commentDelete' onclick='deleteComment(\"" + cid + "\")'>删除</button>";
                        }

                        let end = "</div>"+"<br><hr class='hr'>";
                        single+=end;

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

        function deleteComment(cid){
            console.log("jinlail"+cid);

            fetch('/Pbhz/manageServlet?action=deleteComment&cid='+cid)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text(); // assuming server returns JSON
                })
                .then(data => {
                    // Handle the response data as needed
                    console.log("data:"+data);
                    loadComment();
                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });


        }

        function deleteQuestion(qid){
            console.log("进入了qid")

            fetch('http://localhost:8080/Pbhz/manageServlet?action=deleteQuestion&qid='+qid)
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
                    // window.location.go(-1);
                    // window.location.reload();
                    // window.history.back();
                    // window.location.reload(true);
                    window.location.replace(document.referrer);

                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });

        }

    </script>

</body>
</html>
