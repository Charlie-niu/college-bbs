package com.charlie.college_bbs.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.*;


public class Base64Utils {

    public static final String imgFilePath = "F:\\articlePic\\";
    /**
     * 图片转化成base64字符串
     * @param imgPath
     * @return
     */
    public static String ImageToBase64Str(String imgPath) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        int pos = imgPath.lastIndexOf(".");
        String suffixName = imgPath.substring(pos+1);

        String imgFile = imgPath;// 待处理的图片
        String prefixString = "data:image/" + suffixName + ";base64,";

        InputStream in = null;
        byte[] data = null;
        String encode = null; // 返回Base64编码过的字节数组字符串
        // 对字节数组Base64编码
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = prefixString + Base64.encodeBase64String(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * base64字符串转化成图片
     * @param imgData 图片编码
     * @return String
     * @throws IOException
     */
    public static String Base64StrToImage(String imgData, String  picName) {
        // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return null;

        //获取图片的后缀名
        int pos = picName.lastIndexOf(".");
        String filename = picName.substring(0, pos);
        String suffixName = picName.substring(pos+1);
        String randomStr = GenRandomString.generater(5, GenRandomString.All);

        //去掉前面的“data:image/jpeg;base64,”的字样
        String[] str = imgData.split(",");

        byte[] data = null;
        if(Base64.isBase64(str[1])){
            //解码base64
            data = Base64.decodeBase64(str[1]);
        } else {
            String replace = str[1].replace(" ", "+");
            data = Base64.decodeBase64(replace);
        }

        //写入保存文件
        String pathName = imgFilePath + filename + "_" + randomStr + "." + suffixName;
        File file = new File( pathName);
        FileOutputStream os = null;
        boolean result = false;
        try {
            for (int i = 0; i < data.length; ++i) {
                // 调整异常数据
                if (data[i] < 0) { data[i] += 256; }
            }
            os = new FileOutputStream(file);
            os.write(data);
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try { os.close();}
                catch (IOException e) { e.printStackTrace(); }
            }
            return pathName;
        }
    }
}