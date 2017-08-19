package indi.yunhan.exam.mission.common;

/**
 * Created by asus on 2017/8/19.
 */
public class YhError {
    private int errorcode;
    private int status;

    private String errorMsg;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public YhError(){

    }

    public YhError(int errorcode, int status, String errorMsg) {
        this.errorcode = errorcode;
        this.status = status;
        this.errorMsg = errorMsg;
    }
}
