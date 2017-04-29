package com.demo.spring.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by web on 18/04/17.
 */
public interface SingerRepository extends JpaRepository<Singer, Long>{


    List<Singer> findBySingernameAndSongname(String singername, String songname);

    @Query("SELECT u FROM Singer u WHERE u.singername LIKE %?1% and u.songname LIKE %?2%")
    List<Singer> searchSingers(String singername, String songname);


// Search query displays all of the singers info.

}
