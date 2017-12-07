package main.java.util;

/**
 * Created by Administrator on 2017/11/17.
 */
public class ErrorException extends RuntimeException {
    private String errorMessage;
    private String code;
    private String suggest;

    public String getMessage() {
        return errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
