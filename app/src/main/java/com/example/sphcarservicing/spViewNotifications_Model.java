package com.example.sphcarservicing;

public class spViewNotifications_Model {
    private String spName;
    private String spAddress;
    private String bdate;
    private String btype;
    private String services;
    private String bookinid;
    private String spemail;
    private String uemail;


    public spViewNotifications_Model(String bid, String sp_name, String sp_add, String b_date,
                                     String b_type, String b_services,String sp_email,String u_email) {
        this.bookinid = bid;
        this.spName = sp_name;
        this.spAddress = sp_add;
        this.bdate = b_date;
        this.btype = b_type;
        this.services = b_services;
        this.spemail = sp_email;
        this.uemail = u_email;
    }

    public String getBookingId() {
        return bookinid;
    }
    public String getSpName() {
        return spName;
    }
    public String getSpAddress(){return spAddress;}
    public String getBdate(){return bdate;}
    public String getBtype(){return btype;}
    public String getservices(){return services;}
    public String getSpemail(){return spemail;}
    public String getUemail(){return uemail;}
}
