package com.lihang.exception;

public class UserNotExitException extends RuntimeException{
    private static final long serialVersionUID = -6112780192479692859L;

    private String id;
    public UserNotExitException(String id){
        super("user "+id+" not exist");
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
