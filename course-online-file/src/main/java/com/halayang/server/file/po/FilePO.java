package com.halayang.server.file.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文件
 * </p>
 *
 * @author YangYuDi
 * @since 2021-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("file")
public class FilePO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 相对路径
     */
    private String path;

    /**
     * 文件名
     */
    private String name;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 大小|字节B
     */
    private Integer size;

    /**
     * 用途|枚举[FileUseEnum]：COURSE(C, 讲师), TEACHER(T, 课程)
     */
    private String useTo;

    /**
     * 已上传分片
     */
    private Integer shardIndex;

    /**
     * 分片大小|B
     */
    private Integer shardSize;

    /**
     * 分片总数
     */
    private Integer shardTotal;

    /**
     * 文件标识
     */
    private String fileKey;

    /**
     * vod|阿里云vod
     */
    private String vod;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime editTime;


}
