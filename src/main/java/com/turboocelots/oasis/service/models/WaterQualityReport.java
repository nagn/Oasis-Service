package com.turboocelots.oasis.service.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by mlin on 4/20/17.
 */
@Entity
public class WaterQualityReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp timestamp;

    private Double longitude;
    private Double latitude;

    private String overallCondition;
    private Double virusPPM;
    private Double contaminantsPPM;

    @ManyToOne
    @JoinColumn(name = "oasis_user_id")
    private OasisUser user;

    protected WaterQualityReport() {}

    public WaterQualityReport(Timestamp timestamp,
                              Double longitude, Double latitude,
                              String overallCondition,
                              Double virusPPM, Double contaminantsPPM) {
        this.timestamp = timestamp;
        this.longitude = longitude;
        this.latitude = latitude;

        this.overallCondition = overallCondition;
        this.virusPPM = virusPPM;
        this.contaminantsPPM = contaminantsPPM;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getOverallCondition() {
        return overallCondition;
    }

    public Double getVirusPPM() {
        return virusPPM;
    }

    public Double getContaminantsPPM() {
        return contaminantsPPM;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setOverallCondition(String overallCondition) {
        this.overallCondition = overallCondition;
    }

    public void setVirusPPM(Double virusPPM) {
        this.virusPPM = virusPPM;
    }

    public void setContaminantsPPM(Double contaminantsPPM) {
        this.contaminantsPPM = contaminantsPPM;
    }

    public OasisUser getUser() {
        return this.user;
    }

    public void setUser(OasisUser user) {
        this.user = user;
    }

    public String getUserName() {
        if (this.user == null) {
            return "";
        } else {
            return this.user.getUserName();
        }
    }
}
