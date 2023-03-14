package com.example.sphcarservicing;

public class SearchModel {
    private String spName;
    private String spAddress;

    public SearchModel(String spName, String spAddress) {
        this.spName = spName;
        this.spAddress = spAddress;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpAddress() {
        return spAddress;
    }

    public void setSpAddress(String spAddress) {
        this.spAddress = spAddress;
    }
}
