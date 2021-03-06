package com.halayang.common.utils.response;

public class ResponseResult {
    private static final String PREFIX_ERROR_PROMPT = "操作失败，";

    private ResponseResult() {
    }

    public static <T> ResponseObject<T> success() {
        return new ResponseObject<>(ResponseCode.REQUEST_SUCCESS);
    }

    public static <T> ResponseObject<T> success(T data) {
        return (new ResponseObject<T>(ResponseCode.REQUEST_SUCCESS)).count(1L).data(data);
    }

    public static <T> ResponseObject<T> success(long total, T data) {
        return (new ResponseObject<T>(ResponseCode.REQUEST_SUCCESS)).count(total).data(data);
    }

    public static <T> ResponseObject<T> error() {
        return (new ResponseObject<>(ResponseCode.REQUEST_ERROR));
    }

    public static <T> ResponseObject<T> error(ResponseCode responseCode) {
        return (new ResponseObject<T>(responseCode)).count(0L).msg(replacePrefixErrorPrompt(responseCode.message()));
    }

    public static <T> ResponseObject<T> error(ResponseCode responseCode, T data) {
        return (new ResponseObject<T>(responseCode)).count(0L).data(data);
    }

    public static String replacePrefixErrorPrompt(String message) {
        return message == null ? "" : message.replace(PREFIX_ERROR_PROMPT, "");
    }

    public static <T> ResponseObject<T> error(String message) {
        return (new ResponseObject<T>()).code(ResponseCode.REQUEST_ERROR).msg(PREFIX_ERROR_PROMPT + replacePrefixErrorPrompt(message)).count(0L);
    }

    public static <T> ResponseObject<T> error(String message, T data) {
        return (new ResponseObject<T>()).code(ResponseCode.REQUEST_ERROR).msg(PREFIX_ERROR_PROMPT + replacePrefixErrorPrompt(message)).count(0L).data(data);
    }

    public static <T> ResponseObject<T> error(Integer code, String message) {
        return (new ResponseObject<T>()).code(code).msg(PREFIX_ERROR_PROMPT + replacePrefixErrorPrompt(message)).count(0L);
    }

    public static <T> ResponseObject<T> error(Integer code, String message, T data) {
        return (new ResponseObject<T>()).code(code).msg(PREFIX_ERROR_PROMPT + replacePrefixErrorPrompt(message)).count(0L).data(data);
    }
}
