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

    private OasisUser user;
    protected WaterQualityReport() {}

    public WaterQualityReport(Timestamp timestamp, OasisUser newUser) {
        this.timestamp = timestamp;
        this.user = newUser;
    }

    @ManyToOne
    @JoinColumn(name = "oasis_user_name")
    public String getOasisUserName() {
        return user.getUserName();
    }

}
