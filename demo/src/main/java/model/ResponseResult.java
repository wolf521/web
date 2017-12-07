package main.java.model;

/**
 * Created by Administrator on 2017/11/17.
 */
public class ResponseResult<T> {
    /**
     * 是否成功
     */
    private boolean success = true;
    /**
     * 返回数据
     */
    private T data = null;
    /**
     * 信息
     */
    private String message = null;

    /**
     * 异常信息
     */
    private RuntimeException exception = null;

    public ResponseResult() {}

    public ResponseResult(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RuntimeException getException() {
        return exception;
    }

    public void setException(RuntimeException exception) {
        this.exception = exception;
    }

    public static <T> ResponseResult<T> create(boolean success, T data, String message){
        return new ResponseResult<T>(success,data,message);
    }
}
