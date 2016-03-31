package com.cms.models;

public class StrutsToken {
    private int id;
    private int token=1;
    public StrutsToken() {
        super();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getToken() {
        return token;
    }
    public boolean isValidToken(int token){
        return this.token==token;
    }
}
