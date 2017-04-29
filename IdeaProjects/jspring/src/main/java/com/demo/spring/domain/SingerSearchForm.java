package com.demo.spring.domain;

/**
 * Created by web on 20/04/17.
 */
public class SingerSearchForm {

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


    String singername;
    String songname;

}


// Enter the singername or song name to display the info