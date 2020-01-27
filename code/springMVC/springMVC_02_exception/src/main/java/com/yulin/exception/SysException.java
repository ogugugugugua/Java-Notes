package com.yulin.exception;

public class SysException extends Exception{
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public SysException(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
