package com.java.bodysignal;

public class registerDetail {
    String name, id,password;

    public registerDetail(){

    }
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }
    public void setPassword(String pwd){
        this.password=pwd;
    }
    public String getPassword(){
        return this.password;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

}
