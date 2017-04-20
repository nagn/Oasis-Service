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
    private String password;
    private String fullName;
    private String userType;
    private String email;
    private String homeAddress;
    private String title;
    private String phoneNumber;

    protected OasisUser() {}

    public OasisUser(String userName,
                     String password,
                     String userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return String.format(
                "OasisUser[id=%d, userName='%s', password='%s', userType='%s]",
                id, userName, password, userType);
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {return fullName;}

    public void setFullName(String name) {this.fullName = name;}

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
