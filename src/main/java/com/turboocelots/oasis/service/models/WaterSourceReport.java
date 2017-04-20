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

    private String waterConditon;
    private String waterType;
}
