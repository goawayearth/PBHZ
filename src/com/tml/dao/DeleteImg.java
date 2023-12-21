package com.tml.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteImg {

    static void deleteImg(String oldPath){
        String filePath = "D:\\STUDY\\1Web\\web作业\\work3"+oldPath;

        // 使用Paths.get()方法创建Path对象
        Path path1 = Paths.get(filePath);

        try {
            // 使用Files.delete()方法删除文件
            Files.delete(path1);
            System.out.println("文件删除成功！");
        } catch (IOException e) {
            System.err.println("删除文件时出错：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
