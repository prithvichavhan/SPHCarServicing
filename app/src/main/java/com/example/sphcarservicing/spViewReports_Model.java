package com.example.sphcarservicing;

public class spViewReports_Model {
    private String username;
    private String useremail;
    private String bdate;
    private String services;


    public spViewReports_Model(String u_name, String u_em, String b_date,
                                String b_services) {
        this.username = u_name;
        this.useremail = u_em;
        this.bdate = b_date;
        this.services = b_services;
    }

    public String getusername() {
        return username;
    }
    public String getuseremail(){return useremail;}
    public String getBdate(){return bdate;}
    public String getservices(){return services;}
}
