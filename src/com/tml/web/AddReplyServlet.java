package com.tml.web;

import com.tml.bean.Comment;
import com.tml.service.impl.CommentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.logging.Logger;

@WebServlet(name = "/addReplyServlet")
@MultipartConfig
public class AddReplyServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddReplyServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String content = request.getParameter("replyContent");
        String qid = request.getParameter("qid");

        // 获取当前时间，将LocalDateTime转换为Date
        LocalDateTime currentTime = LocalDateTime.now();
        // 获取当前登录用户的名字
        HttpSession session = request.getSession();
        String loggedInUserName = (String) session.getAttribute("username");

        // 创建一个 Comment 对象
        Comment reply = new Comment();
        reply.setQid(qid);
        reply.setContent(content);
        reply.setDate(java.sql.Timestamp.valueOf(currentTime));
        reply.setName(loggedInUserName);

        // 处理文件上传
        Part filePart = request.getPart("fileUpload");
        if (filePart != null && filePart.getSize() > 0) {
            // 获取文件名和文件内容
            String fileName = getUniqueFileName(filePart);
            System.out.println(fileName);
            InputStream fileContent = filePart.getInputStream();


            // 构建文件路径在服务器上的保存路径。
            String filePath = "D:\\STUDY\\1Web\\web作业\\work3\\uploads\\" + File.separator + fileName;

            System.out.println("路径"+filePath);

            // 将文件保存到服务器
            saveFile(fileContent, filePath);

            // 设置文件信息到 Comment 对象
            String filepath1 = "/uploads/" + File.separator + fileName;
            reply.setFileName(filepath1);
//            reply.setFileType(filePart.getContentType());
//            reply.setFileContent(readFile(fileContent));
        }

        // 调用 CommentService 中的方法将评论保存到数据库
        CommentServiceImpl commentService = new CommentServiceImpl();
        commentService.addComment(reply);

        // 添加回复到数据库成功后
        // 设置一个属性或使用Session标记回复成功,目的是为了更新页面，添加新的回复在页面上
//        request.setAttribute("replySuccess", true);

        // 请求转发允许你将请求传递给另一个资源，并在同一个请求中处理。
//        request.getRequestDispatcher("reply.jsp?id=" + qid).forward(request, response);
        response.getWriter().write("success");
    }

    private void saveFile(InputStream inputStream, String filePath) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("文件保存成功");
        }
    }

    private String getUniqueFileName(Part part) {
        String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        return uniqueFileName;
    }

    private byte[] readFile(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, bytesRead);
            }
            return result.toByteArray();
        }
    }
}
