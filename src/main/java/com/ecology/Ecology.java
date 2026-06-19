package com.ecology;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/* 
  Колекція, в якій зберігається документ баз даних MongoDB, що представляє запис екології.
*/
@Document(collection = "ecology")
public class Ecology {
    @Id
    private String id;
    private String ecologistName;
    private String sensorId;
    private String measurementDate;
    private Double paramPm25;
    private Double paramNo2;
    private Double paramPh;
    private String dangerLevel;
    private Double latitude;
    private Double longitude;
    private String authorityName;
    private String authorityAddress;
    private String authorityPhone;
    private Integer ecologistExperience;

    public Ecology(
        String ecologistName,
        String sensorId,
        String measurementDate,
        Double paramPm25,
        Double paramNo2,
        Double paramPh,
        String dangerLevel,
        Double latitude,
        Double longitude,
        String authorityName,
        String authorityAddress,
        String authorityPhone,
        Integer ecologistExperience
    ) {
        this.ecologistName = ecologistName;
        this.sensorId = sensorId;
        this.measurementDate = measurementDate;
        this.paramPm25 = paramPm25;
        this.paramNo2 = paramNo2;
        this.paramPh = paramPh;
        this.dangerLevel = dangerLevel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.authorityName = authorityName;
        this.authorityAddress = authorityAddress;
        this.authorityPhone = authorityPhone;
        this.ecologistExperience = ecologistExperience;
    }

    public Ecology() {
    }

    public String getId() {
        return id;
    }

    public String getEcologistName() {
        return ecologistName;
    }

    public void setEcologistName(String ecologistName) {
        this.ecologistName = ecologistName;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(String measurementDate) {
        this.measurementDate = measurementDate;
    }

    public Double getParamPm25() {
        return paramPm25;
    }

    public void setParamPm25(Double paramPm25) {
        this.paramPm25 = paramPm25;
    }

    public Double getParamNo2() {
        return paramNo2;
    }

    public void setParamNo2(Double paramNo2) {
        this.paramNo2 = paramNo2;
    }

    public Double getParamPh() {
        return paramPh;
    }

    public void setParamPh(Double paramPh) {
        this.paramPh = paramPh;
    }

    public String getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(String dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityAddress() {
        return authorityAddress;
    }

    public void setAuthorityAddress(String authorityAddress) {
        this.authorityAddress = authorityAddress;
    }

    public String getAuthorityPhone() {
        return authorityPhone;
    }

    public void setAuthorityPhone(String authorityPhone) {
        this.authorityPhone = authorityPhone;
    }

    public Integer getEcologistExperience() {
        return ecologistExperience;
    }

    public void setEcologistExperience(Integer ecologistExperience) {
        this.ecologistExperience = ecologistExperience;
    }

    public String toString() {
        return "Ecology {" +
                " id=\"" + id + "\"\n" +
                " ecologistName=\"" + ecologistName + "\"\n" +
                " sensorId=\"" + sensorId + "\"\n" +
                " measurementDate=\"" + measurementDate + "\"\n" +
                " paramPm25=" + paramPm25 + "\n" +
                " paramNo2=" + paramNo2 + "\n" +
                " paramPh=" + paramPh + "\n" +
                " dangerLevel=\"" + dangerLevel + "\"\n" +
                " latitude=" + latitude + "\n" +
                " longitude=" + longitude + "\n" +
                " authorityName=\"" + authorityName + "\"\n" +
                " authorityAddress=\"" + authorityAddress + "\"\n" +
                " authorityPhone=\"" + authorityPhone + "\"\n" +
                " ecologistExperience=" + ecologistExperience + "\n" +
                "}";
    }
}
