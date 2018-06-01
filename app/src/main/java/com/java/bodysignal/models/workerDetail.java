package com.java.bodysignal.models;

public class workerDetail {
    String manager,name, age,number,phoneNumber;

    public workerDetail(){

    }


    public String getAge(){
        return this.age;
    }
    public void setAge(String age){
        this.age=age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setNumber(String number){
        this.number=number;
    }
    public String getNumber(){
        return this.number;
    }
    public void setPhoneNumber(String number){
        this.phoneNumber=phoneNumber;
    }
    public String getphoneNumber(){
        return this.phoneNumber;
    }
    public void setManager(String manager){this.manager=manager;}
    public String getManager(){return this.manager;}

}
