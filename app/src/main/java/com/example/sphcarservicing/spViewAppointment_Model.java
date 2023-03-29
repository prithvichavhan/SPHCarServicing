package com.example.sphcarservicing;

public class spViewAppointment_Model {
    private String spName;
    private String spAddress;
    private String bdate;
    private String btype;
    private String services;
    private Integer bookinid;


    public spViewAppointment_Model(Integer bid,String sp_name, String sp_add, String b_date,
                                   String b_type, String b_services) {
        this.bookinid = bid;
        this.spName = sp_name;
        this.spAddress = sp_add;
        this.bdate = b_date;
        this.btype = b_type;
        this.services = b_services;
    }

    public Integer getBookingId() {
        return bookinid;
    }
    public String getSpName() {
        return spName;
    }
    public String getSpAddress(){return spAddress;}
    public String getBdate(){return bdate;}
    public String getBtype(){return btype;}
    public String getservices(){return services;}
}
