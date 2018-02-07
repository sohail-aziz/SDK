
package com.korvac.liquidpay.sdk.data.response;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class CardNon3D extends BaseCard {

    @SerializedName("bin")
    private String bin;
    @SerializedName("card_brand")
    private String card_brand;
    @SerializedName("card_type")
    private String card_type;
    @SerializedName("cardholder")
    private String cardholder;
    @SerializedName("currency")
    private String currency;
    @SerializedName("expiry_month")
    private String expiry_month;
    @SerializedName("expiry_year")
    private String expiry_year;
    @SerializedName("id")
    private String id;
    @SerializedName("issuer")
    private String issuer;
    @SerializedName("last_four")
    private String last_four;
    @SerializedName("status")
    private String status;

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getCard_brand() {
        return card_brand;
    }

    public void setCard_brand(String card_brand) {
        this.card_brand = card_brand;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExpiry_month() {
        return expiry_month;
    }

    public void setExpiry_month(String expiry_month) {
        this.expiry_month = expiry_month;
    }

    public String getExpiry_year() {
        return expiry_year;
    }

    public void setExpiry_year(String expiry_year) {
        this.expiry_year = expiry_year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getLast_four() {
        return last_four;
    }

    public void setLast_four(String last_four) {
        this.last_four = last_four;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CardNon3D{" +
                "bin='" + bin + '\'' +
                ", card_brand='" + card_brand + '\'' +
                ", card_type='" + card_type + '\'' +
                ", cardholder='" + cardholder + '\'' +
                ", currency='" + currency + '\'' +
                ", expiry_month='" + expiry_month + '\'' +
                ", expiry_year='" + expiry_year + '\'' +
                ", id='" + id + '\'' +
                ", issuer='" + issuer + '\'' +
                ", last_four='" + last_four + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
