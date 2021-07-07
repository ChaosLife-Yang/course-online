package com.halayang.server.file.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author YangYuDi
 * @version 1.0.0
 * @program course-online
 * @description
 * @create 2021/1/12 15:44
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/12 15:44           1.0
 */
@Slf4j
public class MD5Util {

    /**
     * 辅助方法
     *
     * @param inputStream 文件流
     * @return
     */
    private static byte[] createChecksum(InputStream inputStream) {
        InputStream fis = null;
        try {
            fis = inputStream;
            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead = -1;

            while ((numRead = fis.read(buffer)) != -1) {
                complete.update(buffer, 0, numRead);
            }
            return complete.digest();
        } catch (FileNotFoundException e) {
            log.error("文件未找到", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("算法异常", e);
        } catch (IOException e) {
            log.error("文件读取失败", e);
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                log.error("文件读取失败", e);
            }
        }
        throw new IllegalArgumentException("读取异常");

    }

    /**
     * 生成文件的MD5码
     *
     * @param inputStream 文件流
     * @return 该文件的MD5码
     */
    public static String generateMD5(InputStream inputStream) {

        byte[] b = createChecksum(inputStream);
        StringBuilder result = new StringBuilder();
        for (byte value : b) {
            result.append(Integer.toString((value & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return result.toString();
    }

}
