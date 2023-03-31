package com.example.sphcarservicing;

public class Sp_ServiceHistory_Model {
    private String uName;
    private String spAddress;
    private String bdate;
    private String services;
    private String uemail;


    public Sp_ServiceHistory_Model(String sp_name, String sp_add, String b_date, String b_services, String email) {
        this.uName = sp_name;
        this.spAddress = sp_add;
        this.bdate = b_date;
        this.services = b_services;
        this.uemail = email;
    }

    public  String getUemail(){return uemail;}

    public String getSpName() {
        return uName;
    }
    public String getSpAddress(){return spAddress;}
    public String getBdate(){return bdate;}
    public String getservices(){return services;}
}
