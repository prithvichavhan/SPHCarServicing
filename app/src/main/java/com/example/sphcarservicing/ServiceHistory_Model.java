package com.example.sphcarservicing;

public class ServiceHistory_Model {
    private String spName;
    private String spAddress;
    private String bdate;
    private String services;


    public ServiceHistory_Model(String sp_name, String sp_add,String b_date, String b_services) {
        this.spName = sp_name;
        this.spAddress = sp_add;
        this.bdate = b_date;
        this.services = b_services;
    }

    public String getSpName() {
        return spName;
    }
    public String getSpAddress(){return spAddress;}
    public String getBdate(){return bdate;}
    public String getservices(){return services;}
}
