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

    private String reporterName;

    protected WaterQualityReport() {}

    public WaterQualityReport(Timestamp timestamp, String reporterName) {
        this.timestamp = timestamp;
        this.reporterName = reporterName;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getReporterName() {
        return reporterName;
    }
}
