package com.tml.web;

import com.tml.service.impl.UserInfoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet(name="/changeIcon")
@MultipartConfig
public class ChangeIcon extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入了服务器！！！");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println("$$ "+username);
        // 处理文件上传
        Part filePart = request.getPart("fileUpload");
        if (filePart != null && filePart.getSize() > 0) {
            System.out.println("文件有内容");
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
            UserInfoImpl userInfo = new UserInfoImpl();
            userInfo.changeIcon(username,filepath1);

            response.sendRedirect("person.jsp");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private String getUniqueFileName(Part part) {
        String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        return uniqueFileName;
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
