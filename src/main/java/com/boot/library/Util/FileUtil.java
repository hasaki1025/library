package com.boot.library.Util;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static boolean deleteFile(String path) {
        File file = new File(path);
        if(file.exists())
        {
            return file.delete();
        }
        return false;
    }

    public static boolean copyFile(String oldpath,String newpath){
        File newfile = new File(newpath);
        File oldfile = new File(oldpath);
        FileInputStream in = null;
        FileOutputStream os=null;
        try {
            in = new FileInputStream(oldfile);
            os= new FileOutputStream(newfile);
            byte[] bytes = new byte[1024];
            int count = 0;
            while ((count = in.read(bytes)) != -1)
            {
                os.write(bytes,0,count);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {

            if(in != null)
            {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getDownLoadFileName(String filename,HttpServletRequest request) {
        String new_filename = null;
        try {
            new_filename = URLEncoder.encode(filename, "UTF8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String userAgent = request.getHeader("User-Agent");
        // System.out.println(userAgent);
        String rtn = "filename=\"" + new_filename + "\"";
        // 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
        if (userAgent != null) {
            userAgent = userAgent.toLowerCase();
            // IE浏览器，只能采用URLEncoder编码
            if (userAgent.contains("msie")) {
                rtn = "filename=\"" + new_filename + "\"";
            }
            // Opera浏览器只能采用filename*
            else if (userAgent.contains("opera")) {
                rtn = "filename*=UTF-8''" + new_filename;
            }
            // Safari浏览器，只能采用ISO编码的中文输出
            else if (userAgent.contains("safari")) {
                try {
                    rtn = "filename=\""
                            + new String(filename.getBytes(StandardCharsets.UTF_8),
                            "ISO8859-1") + "\"";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            // Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
            else if (userAgent.contains("applewebkit")) {
                try {
                    new_filename = MimeUtility
                            .encodeText(filename, "UTF8", "B");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                rtn = "filename=\"" + new_filename + "\"";
            }
            // FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
            else if (userAgent.contains("mozilla")) {
                rtn = "filename*=UTF-8''" + new_filename;
            }
        }
        return rtn;
    }
}
