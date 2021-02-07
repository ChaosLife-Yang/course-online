package com.halayang.common.utils.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BCrypt加密解密工具类
 *
 * @author YUDI
 * @date 2020/12/1
 */
@ApiModel(
        value = "com.halayang.common.utils.response.ResponseObject<T>",
        description = "状态返回信息"
)
@Accessors(chain = true)
public class ResponseObject<T> {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @ApiModelProperty(
            name = "code",
            value = "响应结果编码"
    )
    private Integer code;
    @ApiModelProperty(
            name = "msg",
            value = "响应结果信息"
    )
    private String msg;
    @ApiModelProperty(
            name = "count",
            value = "统计列表总数（如果列表存在）"
    )
    private long count;
    @ApiModelProperty(
            name = "data",
            value = "响应结果数据"
    )
    private T data;
    @ApiModelProperty(
            name = "data",
            value = "响应结果时间"
    )
    private String time;

    public ResponseObject() {
        this.time = this.sdf.format(new Date());
    }

    public ResponseObject(ResponseCode responseCode) {
        this.time = this.sdf.format(new Date());
        this.code = responseCode.code();
        this.msg = responseCode.message();
    }

    public ResponseObject(ResponseCode responseCode, long count, T data) {
        this.time = this.sdf.format(new Date());
        this.code = responseCode.code();
        this.msg = responseCode.message();
        this.count = count;
        this.data = data;
    }

    public ResponseObject<T> code(ResponseCode responseCode) {
        this.code = responseCode.code();
        this.msg = responseCode.message();
        return this;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public long getCount() {
        return this.count;
    }

    public T getData() {
        return this.data;
    }

    public String getTime() {
        return this.time;
    }

    public ResponseObject<T> code(int code) {
        this.code = code;
        return this;
    }

    public ResponseObject<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResponseObject<T> count(long count) {
        this.count = count;
        return this;
    }

    public ResponseObject<T> data(T data) {
        this.data = data;
        return this;
    }

    public ResponseObject<T> time(String time) {
        this.time = time;
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ResponseObject{sdf=" + this.sdf + ", code=" + this.code + ", msg='" + this.msg + '\'' + ", count=" + this.count + ", data=" + this.data + ", time='" + this.time + '\'' + '}';
    }
}
