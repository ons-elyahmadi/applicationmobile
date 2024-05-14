package com.example.test.models;



public class ProductClass {

    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private String dataImage;
    private String key;
    private double dataPrice;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    public String getDataImage() {
        return dataImage;
    }
    public double getDataPrice() {
        return dataPrice;
    }

    public ProductClass(String dataTitle, String dataDesc, String dataLang, String dataImage , double  dataPrice ) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataImage = dataImage;
        this.dataPrice = dataPrice;
    }
    public ProductClass(){

    }
}