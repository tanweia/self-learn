package com.serius.learn.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.serius.learn.enums.LearnErrorCode;

/**
 * create by tanweia on Jun 8, 2017
 * 业务异常
 */
public class LearnException extends RuntimeException {

    /**
	 * Fields serialVersionUID: TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;

    public LearnException() {
    }

    public LearnException(String msg) {
        super(msg);
    }

    public LearnException(String code, String msg) {
        super(msg);
        this.code = code;
    }
    
    public LearnException(LearnErrorCode errCode) {
    	super(errCode.getMessage());
    	this.code = errCode.getCode();
    }
    
    public LearnException(StringBuilder msg) {
        super(msg == null?null:msg.toString());
    }

    public LearnException(StringBuffer msg) {
        super(msg == null?null:msg.toString());
    }

    public LearnException(Throwable cause) {
        super(cause);
    }

    public LearnException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public LearnException(StringBuilder msg, Throwable cause) {
        super(msg == null?null:msg.toString(), cause);
    }

    public LearnException(StringBuffer msg, Throwable cause) {
        super(msg == null?null:msg.toString(), cause);
    }

    protected LearnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
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
