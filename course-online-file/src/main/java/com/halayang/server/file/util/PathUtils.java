package com.halayang.server.file.util;

import com.halayang.server.file.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
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
@Slf4j
public class PathUtils {
    /**
     * 正斜杠
     */
    public static final String SEPARATOR = "/";

    private PathUtils() {
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
        return new StringBuilder(PathUtils.yearFilePrefix())
                .append(SEPARATOR)
                .append(PathUtils.monthFilePrefix())
                .append(SEPARATOR)
                .append(PathUtils.dayFilePrefix())
                .append(SEPARATOR)
                .toString();
    }

    /**
     * 根据MultipartFile生成要存储的文件路径
     *
     * @param file MultipartFile文件
     * @author YangYudi
     * @date 2021/1/20 15:45
     * @return java.lang.String
     */
    public static String getSaveFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String postName = PathUtils.getExtensionName(originalFilename);
        String parentPath = PathUtils.getTimePath();
        //最终文件名
        String finalName = UUID.randomUUID().toString() + "." + postName;
        return parentPath + finalName;
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
            String postName = getExtensionName(originalFilename);
            String parentPath = PathUtils.getTimePath();
            //最终文件名
            String finalName = UUID.randomUUID().toString() + "." + postName;
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

    /**
     * 获取文件MD5值
     *
     * @param in 文件输入流
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/18 9:01
     */
    public static String getFileMD5(InputStream in) {
        MessageDigest digest = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 获取文件后缀
     *
     * @param filename 文件名
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/18 9:01
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
            return filename.toLowerCase();
        }
        return null;
    }
}
