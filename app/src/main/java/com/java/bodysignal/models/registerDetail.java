package com.java.bodysignal.models;

public class registerDetail {
    String name, id,password;

    private static registerDetail registerObject;

    public registerDetail(){

    }


    public registerDetail(String id,String password){
        this.id=id;
        this.password=password;
    }

    public registerDetail(String id,String name,String pwd){
        this.id=id;
        this.name=name;
        this.password=pwd;
    }
    public static registerDetail getRegisterObject(){
        if (registerObject == null) {
            registerObject =new registerDetail();
        }
        return  registerObject;

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
