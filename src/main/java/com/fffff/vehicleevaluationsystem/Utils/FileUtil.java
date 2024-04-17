package com.fffff.vehicleevaluationsystem.Utils;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    //清空文件夹
    public static void ClearDirectory(String path1) {
        // 指定要清空的文件夹路径
        File directory = new File(path1);

        // 检查文件夹是否存在
        if (directory.exists() && directory.isDirectory()) {
            // 使用try-with-resources语句自动关闭资源
            try {
                // 递归遍历文件夹并删除所有文件
                Files.walk(directory.toPath())
                        .sorted((a, b) -> b.compareTo(a)) // 从子目录开始删除，避免删除父目录时的问题
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                System.out.println("文件夹已清空");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("指定的路径不是一个文件夹或者不存在");
        }
    }

    //创建空文件夹
    public static void CreateDirectoryExample(String path1) {

        // 指定新文件夹的路径
        String directoryPath = path1;

        // 创建File对象
        File newDirectory = new File(directoryPath);

        // 使用mkdirs()方法创建文件夹，如果文件夹的上级目录不存在也会一并创建
        if (newDirectory.mkdirs()) {
            System.out.println("文件夹创建成功");
        } else {
            System.out.println("文件夹创建失败，可能已经存在");
        }
    }

    // 压缩文件夹
    public static void ZipFolder(String path1, String pathresult) {
        String folderPath = path1;

        // 压缩后生成的压缩文件路径及文件名
        String zipFilePath = pathresult;

        try {
            zipFolder(folderPath, zipFilePath);
            System.out.println("Zip file created successfully.");
        } catch (IOException e) {
            System.out.println("Error creating zip file: " + e.getMessage());
        }
    }


    /**
     * 打包压缩文件夹
     *
     * @param folderPath  文件夹路径
     * @param zipFilePath 压缩后的文件路径
     * @throws IOException IO异常
     */
    public static void zipFolder(String folderPath, String zipFilePath) throws IOException {
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFilePath);
            zos = new ZipOutputStream(fos);

            // 递归遍历整个文件夹并添加到压缩包
            addFolderToZip("", new File(folderPath), zos);
        } finally {
            if (zos != null) {
                zos.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 将文件夹及其中的文件递归添加到压缩流中
     *
     * @param parentPath 父级路径
     * @param folder     文件夹
     * @param zos        Zip输出流
     * @throws FileNotFoundException 文件未找到异常
     * @throws IOException           IO异常
     */
    private static void addFolderToZip(String parentPath, File folder, ZipOutputStream zos) throws FileNotFoundException, IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                // 递归添加子文件夹中的文件
                addFolderToZip(parentPath + folder.getName() + "/", file, zos);
            } else {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);

                    // 新建Zip条目并将输入流加入到Zip包中
                    ZipEntry zipEntry = new ZipEntry(parentPath + folder.getName() + "/" + file.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zos.write(bytes, 0, length);
                    }
                } finally {
                    if (fis != null) {
                        fis.close();
                    }
                }
            }
        }
    }


    //删除某一个文件夹内的全部png图片
    public static void deleteAllPngImagesInFolder(String folderPath) {
        // 创建File对象
        File folder = new File(folderPath);

        // 检查文件夹是否存在且为文件夹
        if (folder.exists() && folder.isDirectory()) {
            // 获取文件夹内所有文件的文件名列表
            File[] files = folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    // 过滤出所有以".png"结尾的文件
                    return name.endsWith(".png");
                }
            });

            // 如果存在文件，则逐个删除
            if (files != null) {
                for (File file : files) {
                    file.delete();
                    System.out.println("Deleted: " + file.getName());
                }
            }
        } else {
            System.out.println("The specified path is not a directory or does not exist.");
        }
    }


}
