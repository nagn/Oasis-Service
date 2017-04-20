package com.turboocelots.oasis.service.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by mlin on 4/20/17.
 */
@Entity
public class WaterSourceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp timestamp;

    private String reporterName;

    private Double longitude;
    private Double latitude;

    private String waterCondition;
    private String waterType;
    
    public WaterSourceReport(Timestamp timestamp, String reporterName, Double longitude, Double latitude, String waterCondition, String waterType) {
        this.timestamp = timestamp;
        this.reporterName = reporterName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.waterCondition = waterCondition;
        this.waterType =waterType;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getReporterName() {
        return reporterName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getWaterType() {
        return waterType;
    }

    public String getWaterCondition() {
        return waterCondition;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setWaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }
}
