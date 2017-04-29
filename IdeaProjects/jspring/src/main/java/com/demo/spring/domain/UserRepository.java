package com.demo.spring.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by web on 18/04/17.
 */
public interface UserRepository  extends JpaRepository<User, Long>{


    List<User> findByFirstnameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.firstname LIKE %?1% and u.lastname LIKE %?2%")
    List<User> searchUsers(String firstname, String lastname);

}


// Query to search all of the users, type their firstname or lastname to get their info