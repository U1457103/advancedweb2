package com.demo.spring.controller;


import com.demo.spring.Service.UserService;
import com.demo.spring.domain.LoginForm;
import com.demo.spring.domain.User;
import com.demo.spring.domain.UserSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by web on 18/04/17.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;
    private String dateFormat;

    // Shows the date on every page to the user


    @RequestMapping(value = "/registeru", method = RequestMethod.GET)
    public String registerView(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);
        return "user/registeru";

    }

    // register for the users

    @RequestMapping(value = "/registeru", method = RequestMethod.POST)

    public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("user", user);
            model.addAttribute("message", "Please fill in all Boxes.");
            return "user/registeru";
        }

        userService.save(user);
        return "redirect:/";
    }

    // Validation put in place to ensure all of the boxes are filled in

    @RequestMapping(value = "/loginu", method = RequestMethod.GET)
    public String loginView(Model model)
    {
        LoginForm user = new LoginForm();
        model.addAttribute("user", user);
        return "user/loginu";

    }


    // Login for  users

    @RequestMapping(value = "/loginu", method = RequestMethod.POST)

    public String login(Model model, @Valid @ModelAttribute("user") LoginForm user, BindingResult bindingResult,
                        HttpSession session)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("message", "Please fill in all Boxes.");
            return "user/loginu";
        }
        if (userService.validateLogin(user)==null || userService.validateLogin(user).size()==0)
        {
            model.addAttribute("user", user);
            model.addAttribute("message", "Incorrect Details.");
            return "user/loginu";
        }

        session.setAttribute("loginu", true);

        return "redirect:/";
    }

    // Validation to ensure correct details are put in aswell as to ensure users fill in all fields

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutView(Model model, HttpSession session)
    {
       session.removeAttribute("loginu");
        return "redirect:/user/loginu";

    }

    // Ensure the user is logged out

    @RequestMapping(value ="/search", method = RequestMethod.GET)
    public String searchView(Model model)
    {
        UserSearchForm searchForm = new UserSearchForm();
        model.addAttribute("searchCriteria", searchForm);
        return "user/search";

    }

    // Users are able to search for other users on the website

    @RequestMapping(value ="/search", method = RequestMethod.POST)
    public String searchView(Model model, @ModelAttribute("searchCriteria") UserSearchForm searchForm)
    {
        List<User> users =  userService.searchUsers(searchForm);
        model.addAttribute("searchCriteria", searchForm);
        model.addAttribute("users", users);
        return "user/search";

    }

    // Search results are displayed to the user

    @RequestMapping(value ="/updateu/{u}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable User u){

        model.addAttribute("user", u);
        return "user/updateu";

    }



    @RequestMapping(value ="/updateu", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("user") User u){

        userService.save(u);
        return "redirect:/";

    }


    @RequestMapping(value ="/delete/{user}", method =RequestMethod.GET)


    public String delete(@PathVariable User user, HttpSession session)
    {
        if(session.getAttribute("loginu") !=null && session.getAttribute("loginu")=="admin")
        {
            return "redirect:/";
        }
        session.setAttribute("loginu", true);
        return "redirect:/";
    }

    // Tested code for only admins to only delete users, sadly did not work.


}
