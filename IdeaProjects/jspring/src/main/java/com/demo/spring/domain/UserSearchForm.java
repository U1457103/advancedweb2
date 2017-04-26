package com.demo.spring.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by web on 20/04/17.
 */
public class UserSearchForm {
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

    String firstname;
    String lastname;

}
