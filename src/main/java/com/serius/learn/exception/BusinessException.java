package com.serius.learn.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.serius.learn.enums.LearnErrorCode;

/**
 * create by tanweia on Jun 8, 2017
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    /**
	 * Fields serialVersionUID: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;

    public BusinessException() {
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
    }
    
    public BusinessException(LearnErrorCode errCode) {
    	super(errCode.getMessage());
    	this.code = errCode.getCode();
    }
    
    public BusinessException(StringBuilder msg) {
        super(msg == null?null:msg.toString());
    }

    public BusinessException(StringBuffer msg) {
        super(msg == null?null:msg.toString());
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BusinessException(StringBuilder msg, Throwable cause) {
        super(msg == null?null:msg.toString(), cause);
    }

    public BusinessException(StringBuffer msg, Throwable cause) {
        super(msg == null?null:msg.toString(), cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getMessage() {
        return buildMessage(super.getMessage(), this.getCause());
    }

    public String getCode() {
        return this.code;
    }

    public void printStackTrace(PrintStream ps) {
        if(this.getCause() == null) {
            super.printStackTrace(ps);
        } else {
            ps.println(this);
            ps.print("原因: ");
            this.getCause().printStackTrace(ps);
        }

    }

    public void printStackTrace(PrintWriter pw) {
        if(this.getCause() == null) {
            super.printStackTrace(pw);
        } else {
            pw.println(this);
            pw.print("原因: ");
            this.getCause().printStackTrace(pw);
        }

    }

    public static String buildMessage(String message, Throwable cause) {
        if(cause != null) {
            StringBuilder buf = new StringBuilder();
            if(message != null) {
                buf.append(message).append("; ");
            }

            buf.append("嵌套的异常是 ").append(cause);
            return buf.toString();
        } else {
            return message;
        }
    }

}
