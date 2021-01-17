package com.halayang.server.file.util;

import com.halayang.server.file.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * copyright (C), 2021, 北京同创永益科技发展有限公司
 *
 * @author YangYuDi
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * YangYuDi               2021/1/12 15:44           1.0
 * @program course-online
 * @description
 * @create 2021/1/12 15:44
 */
public class FileUtils {

    private FileUtils() {
    }

    /**
     * 获取今日日期
     *
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/8 14:51
     */
    public static String yearFilePrefix() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
    }

    /**
     * 获取今日日期
     *
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/8 14:51
     */
    public static String monthFilePrefix() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    /**
     * 获取今日日期
     *
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/8 14:51
     */
    public static String dayFilePrefix() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd"));
    }

    /**
     * 获得年月日路径
     *
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/12 15:51
     */
    public static String getTimePath() {
        return new StringBuilder(FileUtils.yearFilePrefix())
                .append("/")
                .append(FileUtils.monthFilePrefix())
                .append("/")
                .append(FileUtils.dayFilePrefix())
                .append("/")
                .toString();
    }

    /**
     * 保存到本地的文件，返回相对路径
     *
     * @param file     文件
     * @param filePath 要保存的实际路径
     * @return FileDTO
     * @author YangYudi
     * @date 2021/1/16 11:54
     */
    public static FileDTO saveMultipartFile(MultipartFile file, String filePath) {
        try {
            String originalFilename = file.getOriginalFilename();
            String postName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String parentPath = FileUtils.getTimePath();
            //最终文件名
            String finalName = UUID.randomUUID().toString() + postName;
            String finalPath = filePath + parentPath + finalName;
            File dest = new File(finalPath);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            //返回dto对象，封装保存后的文件名，后缀名，文件大小，相对路径
            return new FileDTO()
                    .setName(originalFilename)
                    .setSuffix(postName)
                    .setSize(Math.toIntExact(file.getSize()))
                    .setPath(parentPath + finalName);
        } catch (Exception e) {
            throw new IllegalArgumentException("保存文件出错");
        }
    }

    public static String getFileMD5(File file) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

}
