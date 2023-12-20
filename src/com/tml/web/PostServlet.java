package com.tml.web;

import com.tml.bean.Question;
import com.tml.service.impl.QuestionServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet(name="postServlet")
@MultipartConfig

public class PostServlet extends HttpServlet {

    /* qid   content  date  type  num=0   name   filepath
    记录一条question，需要
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        System.out.println("进入了问题保存服务器");

        Question question = new Question();

        String content = request.getParameter("Content");
        System.out.println("问题的内容是："+content);
        question.setContent(content);
        String qid = UUID.randomUUID().toString();
        question.setQid(qid);
        // 获取当前时间，将LocalDateTime转换为Date
        LocalDateTime currentTime = LocalDateTime.now();
        question.setDate(java.sql.Timestamp.valueOf(currentTime));
        String name = request.getParameter("uid");
        System.out.println("名字是："+name);
        question.setName(name);
        String type = request.getParameter("category");
        question.setType(type);
        System.out.println("问题的类型是："+type);
        int num = 0;
        question.setNum(num);

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
            question.setFilepath(filepath1);

        }
        QuestionServiceImpl questionService = new QuestionServiceImpl();
        questionService.saveQuestion(question);

//        request.getRequestDispatcher("post.jsp").forward(request, response);
        response.getWriter().write("success");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
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
