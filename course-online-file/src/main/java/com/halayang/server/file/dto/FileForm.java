package com.halayang.server.file.dto;

import lombok.Data;

/**
 * 断点上传和分片上传文件相关的参数类
 *
 * @author YangYuDi
 * @since 2021-01-12 21:08:22
 */
@Data
public class FileForm {

    private String md5;

    private String uuid;

    private String date;

    private String name;

    private String size;

    private String total;

    private String index;

    private String action;

    private String partMd5;
}