package com.halayang.common.utils.response;

public class ResponseResult {
    private static final String PREFIX_ERROR_PROMPT = "操作失败，";

    private ResponseResult() {
    }

    public static <T> ResponseObject<T> success() {
        return new ResponseObject(ResponseCode.REQUEST_SUCCESS);
    }

    public static <T> ResponseObject<T> success(T data) {
        return (new ResponseObject(ResponseCode.REQUEST_SUCCESS)).count(1L).data(data);
    }

    public static <T> ResponseObject<T> success(long total, T data) {
        return (new ResponseObject(ResponseCode.REQUEST_SUCCESS)).count(total).data(data);
    }

    public static <T> ResponseObject<T> error() {
        return (new ResponseObject(ResponseCode.REQUEST_ERROR)).count(0L).msg(ResponseCode.REQUEST_ERROR.message()).data((Object)null);
    }

    public static <T> ResponseObject<T> error(ResponseCode responseCode) {
        return (new ResponseObject(responseCode)).count(0L).msg(replacePrefixErrorPrompt(responseCode.message())).data((Object)null);
    }

    public static <T> ResponseObject<T> error(ResponseCode responseCode, T data) {
        return (new ResponseObject(responseCode)).count(0L).data(data);
    }

    public static String replacePrefixErrorPrompt(String message) {
        return message == null ? "" : message.replace("操作失败，", "");
    }

    public static <T> ResponseObject<T> error(String message) {
        return (new ResponseObject()).code(ResponseCode.REQUEST_ERROR).msg("操作失败，" + replacePrefixErrorPrompt(message)).count(0L).data((Object)null);
    }

    public static <T> ResponseObject<T> error(String message, T data) {
        return (new ResponseObject()).code(ResponseCode.REQUEST_ERROR).msg("操作失败，" + replacePrefixErrorPrompt(message)).count(0L).data(data);
    }

    public static <T> ResponseObject<T> error(Integer code, String message) {
        return (new ResponseObject()).code(code).msg("操作失败，" + replacePrefixErrorPrompt(message)).count(0L).data((Object)null);
    }

    public static <T> ResponseObject<T> error(Integer code, String message, T data) {
        return (new ResponseObject()).code(code).msg("操作失败，" + replacePrefixErrorPrompt(message)).count(0L).data(data);
    }
}
