package com.fffff.vehicleevaluationsystem.Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGeneratorUtil {



    /**
     * 生成二维码的方法
     * @param content 二维码内容
     * @param path 二维码图片保存路径
     */
    public static void code(String content,String path){
        //设置二维码宽
        int width = 400;
        //设置二维码高
        int height = 400;
        //设置二维码的后缀名称
        String format = "png";
        //设置map集合要往二维码内添加的参数
        @SuppressWarnings("rawtypes")
        Map map = new HashMap();
        //设置二维码的级别
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置二维码中文本的编码格式
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //设置二维码的外边框
        map.put(EncodeHintType.MARGIN, 1);
        try {
            //创建生成二维码对象，调用方法将所需要的参数放入
            BitMatrix bm = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,map);
            //创建path对象将物理地址放到file文件内，然后生成path对象
            Path paths = new File(path).toPath();
            //使用writeToPath方法调用下载，里面的参数是下载的对象照片，下载的后缀名称，下载的物理路径地址
            MatrixToImageWriter.writeToPath(bm, format, paths);
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }














/*

    public static void QRCodeGenerator() {

        // 要编码的数据
        String data = "https://www.example.com";

        // 创建一个二维码生成器
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            // 编码数据
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            // 将BitMatrix转换为BufferedImage对象
            int width = bitMatrix.getWidth();
            BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < width; y++) {
                for (int x = 0; x < width; x++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            // 保存二维码图片
            File qrCodeFile = new File("example.png");
            ImageIO.write(image, "png", qrCodeFile);
            System.out.println("二维码已成功生成并保存为: " + qrCodeFile.getAbsolutePath());
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
*/

}
