package com.halayang.server.file.util;

import com.halayang.server.file.dto.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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
    private static final char[] HEXDIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f'};

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
     * 获得保存相对路径
     *
     * @param fileName 文件名
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/24 15:04
     */
    public static String getSaveFileName(String fileName) {
        String parentPath = PathUtils.getTimePath();
        //最终文件名
        return new StringBuilder().append(parentPath).append(fileName).toString();
    }

    /**
     * 根据MultipartFile生成要存储的文件路径
     *
     * @param file MultipartFile文件
     * @return java.lang.String
     * @author YangYudi
     * @date 2021/1/20 15:45
     */
    public static String getSaveFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String postName = PathUtils.getExtensionName(originalFilename);
        String parentPath = PathUtils.getTimePath();
        //最终文件名
        String finalName = UUID.randomUUID().toString() + "." + postName;
        return new StringBuilder().append(parentPath).append(finalName).toString();
    }

    /**
     * 根据dto保存文件
     *
     * @param fileDTO  dto对象
     * @param filePath 保存路径
     * @return void
     * @author YangYudi
     * @date 2021/1/24 15:06
     */
    public static String saveMultipartFile(FileDTO fileDTO, String filePath) {
        try {
            MultipartFile file = fileDTO.getFile();
            String saveName = PathUtils.getSaveFileName(fileDTO.getNewName());
            //标序号，表示第几个分片
            String finalPath = String.format("%s/%s.%d", filePath, saveName, fileDTO.getShardIndex());
            File dest = new File(finalPath);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
            return saveName;
        } catch (IOException e) {
            log.error("", e);
            throw new IllegalArgumentException("文件保存出异常了");
        }
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
            String parentPath = getTimePath();
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
    public static String getFileMd5(InputStream in) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = (FileInputStream) in;
            FileChannel fChannel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
            //只读取第一个分片 这样效率高一点 就不用把整个文件读取了
            while (fChannel.read(buffer) != -1) {
                buffer.flip();
                md.update(buffer);
                buffer.compact();
            }
            byte[] b = md.digest();
            return byteToHexString(b);
        } catch (Exception e) {
            log.error("获取文件md5异常", e);
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private static String byteToHexString(byte[] tmp) {
        String s;
        // 用字节表示就是 16 个字节
        // 每个字节用 16 进制表示的话，使用两个字符，
        char[] str = new char[16 * 2];
        // 所以表示成 16 进制需要 32 个字符
        // 表示转换结果中对应的字符位置
        int k = 0;
        // 从第一个字节开始，对 MD5 的每一个字节
        for (int i = 0; i < 16; i++) {
            // 转换成 16 进制字符的转换
            // 取第 i 个字节
            byte byte0 = tmp[i];
            // 取字节中高 4 位的数字转换,
            str[k++] = HEXDIGITS[byte0 >>> 4 & 0xf];
            // >>> 为逻辑右移，将符号位一起右移
            // 取字节中低 4 位的数字转换
            str[k++] = HEXDIGITS[byte0 & 0xf];
        }
        // 换后的结果转换为字符串
        s = new String(str);
        return s;
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
