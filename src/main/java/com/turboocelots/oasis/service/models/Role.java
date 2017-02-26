package com.turboocelots.oasis.service.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mlin on 2/25/17.
 */
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy="roles", cascade = CascadeType.PERSIST)
    private Set<OasisUser> oasisUsers = new HashSet<>();

    protected Role() {} //Needed for jpa

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addOasisUsers(Collection<OasisUser> newUsers) {
        oasisUsers.addAll(newUsers);
    }

    public void removeOasisUsers(Collection<OasisUser> removedUsers) {
        oasisUsers.removeAll(removedUsers);
    }

    @Override
    public String toString() {
        return String.format(
                "Role[id=%d, name='%s']",
                id, name);
    }
}
