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

    @ManyToOne
    @JoinColumn(name = "oasis_id")
    private OasisUser user;

    protected WaterQualityReport() {}

    public WaterQualityReport(Timestamp timestamp, OasisUser user) {
        this.timestamp = timestamp;
        this.user = user;
    }

    public OasisUser getUser() {
        return user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
