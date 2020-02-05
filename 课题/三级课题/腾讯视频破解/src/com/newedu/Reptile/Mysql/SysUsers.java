package com.newedu.Reptile.Mysql;

public class SysUsers {

    private int id;
    private String name;
    private String type;
    private String number;
    private String link;
    private String spare;

    public String[] getall() {
        String[] all={id+"", name, type, number, link, spare};
        return all;
    }

    public int getlength(){
        return 6;
    }

    public String getone(int n) {
        if(n==0){
            String s=""+id;
            return s;
        }else if(n==1){
            return name;
        }else if(n==2){
            return type;
        }else if(n==3){
            return number;
        }else if(n==4){
            return link;
        }else if(n==5){
            return spare;
        }else{
            return null;
        }
    }

    public void setone(int n,String s) {
        if(n==0){
            this.id = new Integer(s);
        }else if(n==1){
            this.name = s;
        }else if(n==2){
            this.type = s;
        }else if(n==3){
            this.number = s;
        }else if(n==4){
            this.link = s;
        }else if(n==5){
            this.spare = s;
        }else{
        }
    }

//    public  SysUsers getall(String[] sy) {
//
//        String[] all={sy.getId()+"",sy.getWy(),
//                sy.getYxdh(),sy.getYx(),
//                sy.getZydh(),sy.getZy(),
//                sy.getZf(),sy.getYw(),
//                sy.getSx(),sy.getWy()};
//        return all;
//    }

    public  String[] getall(SysUsers sy) {
        String[] all={sy.getId()+"",sy.getName(),
                sy.getType(),sy.getNumber(),
                sy.getLink(),sy.getSpare()};
        return all;
    }



    public SysUsers getall(String[] s) {
        SysUsers ss=new SysUsers();
        ss.id = Integer.parseInt(s[0]);
        ss.name = s[1];
        ss.type = s[2];
        ss.number = s[3];
        ss.link = s[4];
        ss.spare = s[5];
        return ss;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setSpare(String spare) {
        this.spare = spare;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getLink() {
        return link;
    }

    public String getSpare() {
        return spare;
    }

    @Override
    public String toString() {
        return "SysUsers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", link='" + link + '\'' +
                ", spare='" + spare + '\'' +
                '}';
    }
    public String toString(int a) {
        return id + name +  type + number +  link + spare +"\n" ;
    }
}
