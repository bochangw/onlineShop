package com.laioffer.onlineShop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 2681531852204068105L;

    @Id
    private String emailId;
    private String password;
    private boolean enabled;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Customer customer;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
