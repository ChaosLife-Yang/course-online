package com.halayang.common.utils.response;

public enum ResponseCode {
    /**
     * 各种响应状态
     */
    REQUEST_SUCCESS(200, "操作成功"),
    REQUEST_ERROR(201, "请求失败"),
    REQUEST_FORBIDDEN(202, "请求拒绝"),
    REQUEST_NOT_FOUND(203, "请求未找到"),
    REQUEST_BAD(204, "错误的请求"),
    REQUEST_TIMEOUT(205, "请求超时"),
    UNAUTHORIZED(401, "未授权"),
    UNAUTHORIZED_TOKEN(402, "TOKEN超时"),
    FORBIDDEN(403, "拒绝访问"),
    NOT_FOUND(404, "无法找到这个资源"),
    METHOD_NOT_ALLOWED(405, "请求方法错误"),
    UNSUPPORTED_MEDIA_TYPE(415, "请求不支持该媒体类型"),
    EXCEPTION(500, "服务器内部错误"),
    PARAM_ERROR(502, "参数错误"),
    BUSINESS_ERROR(503, "业务异常"),
    RPC_ERROR(504, "网络出问题"),
    INVALID_REQUEST(600, "无效的请求"),
    UNAUTHORIZED_CLIENT(601, "客户端未认证"),
    INVALID_CLIENT(602, "无效的客户端"),
    INVALID_GRANT(603, "无效的授权"),
    UNSUPPORTED_GRANT_TYPE(604, "不支持的授权类型"),
    USER_NOT_EXIST(605, "用户不存在"),
    INCORRECT_CLIENT_PASSWORD(606, "客户端密码错误"),
    INCORRECT_USER_PASSWORD(607, "用户密码错误"),
    INVALID_SCOPE(608, "scope范围错误"),
    INVALID_TOKEN(609, "无效的token"),
    INVALID_AUTHORIZATION_CODE(610, "无效的校验码"),
    REDIRECT_MISMATCH(611, "跳转链接不匹配"),
    INCORRECT_TOKEN_JSON(612, "token不是JSON格式"),
    INVALID_CLIENT_TOKEN(613, "客户端client和refresh_token不匹配"),
    INVALID_REFRESH_TOKEN(614, "无效的refresh token"),
    USER_UNUSED(615, "用户已停用"),
    FILE_NOT_EXIST(701, "文件不存在"),
    FILE_TYPE_INVALID(702, "文件类型错误"),
    FILE_CONTENT_INVALID(703, "文件内容错误"),
    FILE_READ_FIELD(704, "文件解析错误"),
    HEADER_REFERER_INVALID(801, "请求头中Referer无效");

    private final Integer code;
    private final String message;

    private ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
