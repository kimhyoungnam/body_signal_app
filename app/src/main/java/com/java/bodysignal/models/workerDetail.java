package com.java.bodysignal.models;

public class workerDetail {
    String manager,name, age,number,phoneNumber,temperature,pulse;

    private static workerDetail workerObject;
    public workerDetail(){}
    public workerDetail(String name,String number,String age){
        this.name=name;
        this.number=number;
        this.age=age;

    }
    public workerDetail(String name,String number,String age,String temp,String pulse){
        this.name=name;
        this.number=number;
        this.age=age;
        this.temperature=temp;
        this.pulse=pulse;

    }
    public static workerDetail getWorkerObject(){
        if (workerObject == null) {
            workerObject =new workerDetail();
        }
        return  workerObject;

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
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setManager(String manager){this.manager=manager;}
    public String getManager(){return this.manager;}
    public void setPulse(String a){this.pulse=a;}
    public String getTemperature(){return this.temperature;}
    public void setTemperature(String t){this.temperature=t;}
    public String getPulse(){return  this.pulse;}

}
