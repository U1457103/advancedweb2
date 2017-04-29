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
public class Singer {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty
    String singername;
    @NotEmpty
    String songname;
    @NotEmpty
    String addcomment;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getAddcomment() {
        return addcomment;
    }

    public void setAddcomment(String addcomment) {
        this.addcomment = addcomment;
    }



// Singers info, User can add/edit and delete on the website.



}
