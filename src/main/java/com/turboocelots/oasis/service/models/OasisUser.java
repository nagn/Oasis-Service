package com.turboocelots.oasis.service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

/**
 * Created by mlin on 2/25/17.
 */
@Entity
public class OasisUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private ArrayList<String> permissions;
    private String password;
    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Role> roles = new HashSet<>();

    protected OasisUser() {}

    public OasisUser(String userName,
                     String password, ArrayList<String> permissions) {
        this.userName = userName;
        this.password = password;
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return String.format(
                "OasisUser[id=%d, userName='%s', password='%s']",
                id, userName, password);
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void addRoles(Collection<Role> newRoles) {
        roles.addAll(newRoles);
    }

    public void removeRoles(Collection<Role> oldRoles) {
        roles.removeAll(oldRoles);
    }

}
