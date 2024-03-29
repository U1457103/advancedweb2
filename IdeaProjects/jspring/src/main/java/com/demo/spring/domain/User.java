package com.demo.spring.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by web on 18/04/17.
 */
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty
    String firstname;
    @NotEmpty
    String lastname;
    @NotEmpty
    String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


// User register, Must fill in all of these fields. Id is auto generated and each user has a unique id


}
