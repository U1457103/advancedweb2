package com.demo.spring.Service;

import com.demo.spring.domain.LoginForm;
import com.demo.spring.domain.UserSearchForm;
import com.demo.spring.domain.User;
import com.demo.spring.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by web on 18/04/17.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User u) { return userRepository.save(u); }


    public List<User> findAll(){ return userRepository.findAll();}

    public void delete(User user) { userRepository.delete(user);}

    public List<User> validateLogin(LoginForm user){

        return userRepository.findByFirstnameAndPassword(user.getUsername(), user.getPassword());
    }

    public List<User> searchUsers(UserSearchForm user)
    {

        return userRepository.searchUsers(user.getFirstname(), user.getLastname());

    }
}
