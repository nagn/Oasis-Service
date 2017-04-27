package com.turboocelots.oasis.service.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Created by mlin on 4/24/17.
 */
@Entity
public class SecurityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp timestamp;
    private String log;

    protected SecurityLog() {}

    public SecurityLog(String log) {
        this.timestamp = Timestamp.from(Instant.now());
        this.log = log;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public Long getID () {
        return id;
    }

    public String getLog() {
        return log;
    }
}
