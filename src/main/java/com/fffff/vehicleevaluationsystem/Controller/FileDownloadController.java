package com.fffff.vehicleevaluationsystem.Controller;

import com.fffff.vehicleevaluationsystem.Server.Impl.EvaluationrecordsServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/file")
public class FileDownloadController {

    @Value("${QRCode.save-path}")
    private String savepath;


    @Autowired
    private EvaluationrecordsServerImpl evaluationrecordsServer;

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile() {

        Integer integer = evaluationrecordsServer.EvaluationRecordQRCodeGeneration();
        System.out.println("integer =>");
        System.out.println(integer);
        String name_PATH = savepath + "\\全部司机对应的二维码(压缩版).zip";


        File file = new File(name_PATH);
        FileSystemResource resource = new FileSystemResource(file);

        HttpHeaders headers = new HttpHeaders();
        //headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=全部司机对应的二维码.zip"); // 指定下载的文件名
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode("学生.xlsx", StandardCharsets.UTF_8)); // 指定下载的文件名(中文需要指定编码)

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}




/*

@RestController
public class FileDownloadController {



    @GetMapping("/download/")
    public void download(
            HttpServletResponse response) {
        System.out.println("ahfhnauoidh");
      */
/*  System.out.println("PublicNetworkAddress ==>");
        System.out.println(PublicNetworkAddress);*//*

        //Integer integer = drivernametoencodedurlServer.GenerateQRCode();

        //File file = new File(StorageLocationOfCompressedPackageFiles );
        File file = new File("C:\\Users\\QQ1839913610\\Desktop\\ewm\\2\\全部司机对应的二维码(压缩版).zip");


        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //文件是否存在
            if (file.exists()) {
                //设置响应
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while (bis.read(buffer) != -1) {
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}


*/
/*
    // 指定要下载的文件路径，根据实际情况进行修改（C:\Users\User\Downloads\file.zip）
    private static final String FILE_PATH = "C:\\Users\\QQ1839913610\\Desktop\\1212\\1.zip";
    //private static final String FILE_PATH = "/path/to/your/file.zip";
    @GetMapping("/api/download/file.zip")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        System.out.println("dfafadf");
        // 读取文件内容
        Path path = Paths.get(FILE_PATH);
        byte[] data = Files.readAllBytes(path);

        // 创建资源对象
        ByteArrayResource resource = new ByteArrayResource(data);

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "file.zip");

        // 返回响应实体
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(resource);
    }*//*
















 */
/*
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileDownloadController {



    // 指定要下载的文件路径，根据实际情况进行修改
    private static final String FILE_PATH = "/path/to/your/file.zip";

    @GetMapping("/api/download/file.zip")
    public ResponseEntity<Resource> downloadFile() throws IOException {
        // 读取文件内容
        Path path = Paths.get(FILE_PATH);
        byte[] data = Files.readAllBytes(path);

        // 创建资源对象
        ByteArrayResource resource = new ByteArrayResource(data);

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "file.zip");

        // 返回响应实体
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(resource);
    }
}

*//*





 */
/* @GetMapping("/api/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Path path = Paths.get("src/main/resources/" + filename); // 指定文件存储路径
        try {
            Resource resource = new InputStreamResource(Files.newInputStream(path));
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }*//*





 */
/*
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileDownloadController {
    @Value("${file.save-path}")
    private String savePath;
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            // 文件路径，你可以根据实际情况设置
            Path filePath = Paths.get("C:\\Users\\QQ1839913610\\Desktop\\clpjxtspbt3v3\\VehicleEvaluationSystem\\src\\main\\resources\\1.txt");
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = "application/octet-stream";
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }




}



*//*




 */
/*


import com.fffff.vehicleevaluationsystem.Server.Impl.DrivernametoencodedurlServerImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileDownloadController {

    private static final String UPLOADED_FILES_DIR = "/path/to/your/uploads"; // 你的文件上传目录

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        File file = new File(UPLOADED_FILES_DIR + File.separator + filename);
        if (file.exists() && !file.isDirectory()) {
            try (InputStream input = new FileInputStream(file)) {
                Resource resource = new InputStreamResource(input);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                        .body(resource);
            } catch (IOException e) {
                log.error("Error occurred while trying to download the file: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while trying to download the file");
            }
        } else {
            log.info("File not found: {}", filename);
            return ResponseEntity.notFound().build();
        }
    }
}



*//*

 */
/*
public class FileController {
    @Value("${PublicNetworkAddress}")
    private String PublicNetworkAddress;
    @Value("${StorageLocationOfCompressedPackageFiles}")
    private String StorageLocationOfCompressedPackageFiles;


    @Autowired

    private DrivernametoencodedurlServerImpl drivernametoencodedurlServer;
    @Value("${file.save-path}")
    private String savePath;


    private static final String UPLOADED_FILES_DIR = "/path/to/your/uploads"; // 你的文件上传目录

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        File file = new File(UPLOADED_FILES_DIR + File.separator + filename);
        if (file.exists() && !file.isDirectory()) {
            try (InputStream input = new FileInputStream(file)) {
                Resource resource = new InputStreamResource(input);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                        .body(resource);
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Error occurred while trying to download the file");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }





*//*
 */
/*











 *//*

 */
/*


 *//*
 */
/*

 *//*

 */
/**
 * 实施大纲下载
 *
 * @param response
 * @param //id
 * @return
 * @throws UnsupportedEncodingException
 *//*
 */
/*
 *//*

 */
/*

    @RequestMapping("/downloadDoc")
    public String downloadDoc(HttpServletResponse response ) throws UnsupportedEncodingException {
        //要下载的文件路径
        File file = new File("C:/Users/QQ1839913610/Desktop/1212/7.txt");
        //下载后的文件名
        String fileName = "下载文件.txt";
        if (file.exists()) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Content-Length",""+file.length());
            // 实现文件下载
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                //logger.info("文件下载完成！！");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }


*//*
 */
/*









 *//*

 */
/*

    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        //这里写要让前端下载的文件的路径
        File file = new File("F:/uploadFiles/test.xlsx");
        //设置编码格式，防止下载的文件内乱码
        response.setCharacterEncoding("UTF-8");
        //获取路径文件对象
        String realFileName = file.getName();
        //设置响应头类型，这里可以根据文件类型设置，text/plain、application/vnd.ms-excel等
        response.setHeader("content-type", "application/octet-stream;charset=UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        //如果不设置响应头大小，可能报错：“Excel 已完成文件级验证和修复。此工作簿的某些部分可能已被修复或丢弃”
        response.addHeader("Content-Length", String.valueOf(file.length()));
        try{
        //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
        //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
        // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
        response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(realFileName.trim(), "UTF-8"));
    } catch (UnsupportedEncodingException e1) {
        e1.printStackTrace();
    }
    //初始化文件流字节缓存
    byte[] buff = new byte[1024];
        try {
        //开始写入
        OutputStream os = response.getOutputStream();
        //写入完成，创建文件流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        // bis.read(data)：将字符读入数组。在某个输入可用、发生I/O错误或者已到达流的末尾前，此方法一直阻塞。
        // 读取的字符数，如果已到达流的末尾，则返回 -1
        int i = bis.read(buff);
        while (i != -1) {
            os.write(buff, 0, buff.length);
            os.flush();
            i = bis.read(buff);
        }
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        if (bis != null) {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


*//*
 */
/*






 *//*

 */
/* @GetMapping("/download")
    public void download(HttpServletResponse response) {
        System.out.println("sdadad");
        // 指定文件路径
       String filePath = "/temp/fileName.jpg";
        // String filePath = "C:\\Users\\QQ1839913610\\Desktop\\1212\\7.txt";
        File file = new File(filePath);
        if (file.exists()) {
            // 设置响应头，使得浏览器能够识别该文件，并进行下载
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("下载成功");
            } catch (Exception e) {
                System.out.println("下载失败");
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

*//*
 */
/*





 *//*

 */
/*

    @GetMapping("/download/{path}")
    public void download(@PathVariable("path") String path ,
                         HttpServletResponse response){
        *//*
 */
/*

 *//*

 */
/*File file = new File("C:\\Users\\Kaven\\Desktop\\ewm\\2\\" + path);*//*
 */
/*
 *//*

 */
/*

        File file = new File("C:\\Users\\QQ1839913610\\Desktop\\new\\");
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //文件是否存在
            if (file.exists()) {
                //设置响应
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while(bis.read(buffer) != -1){
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bis != null) {
                    bis.close();
                }
                if(os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
*//*
 */
/*
















 *//*

 */
/*
    @GetMapping("/download/")
    public void download(
                         HttpServletResponse response){
        System.out.println("PublicNetworkAddress ==>");
        System.out.println(PublicNetworkAddress);
        //Integer integer = drivernametoencodedurlServer.GenerateQRCode();

        //File file = new File(StorageLocationOfCompressedPackageFiles );
        File file = new File("C:\\Users\\QQ1839913610\\Desktop\\ewm\\2\\全部司机对应的二维码(压缩版).zip" );


        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //文件是否存在
            if (file.exists()) {
                //设置响应
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while(bis.read(buffer) != -1){
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bis != null) {
                    bis.close();
                }
                if(os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*//*
 */
/*

 *//*

 */
/*}*//*
 */
/*

 *//*
 */
