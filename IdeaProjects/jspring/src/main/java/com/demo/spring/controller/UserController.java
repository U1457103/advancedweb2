package com.demo.spring.controller;


import com.demo.spring.Service.UserService;
import com.demo.spring.domain.LoginForm;
import com.demo.spring.domain.User;
import com.demo.spring.domain.UserSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping(value = "/registeru", method = RequestMethod.GET)
    public String registerView(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);
        return "user/registeru";

    }

    @RequestMapping(value = "/registeru", method = RequestMethod.POST)

    public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("message", "Please fill in all Boxes.");
            return "user/registeru";
        }

        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/loginu", method = RequestMethod.GET)
    public String loginView(Model model)
    {
        LoginForm user = new LoginForm();
        model.addAttribute("user", user);
        return "user/loginu";

    }

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


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutView(Model model, HttpSession session)
    {
       session.removeAttribute("loginu");
        return "redirect:/user/loginu";

    }

    @RequestMapping(value ="/search", method = RequestMethod.GET)
    public String searchView(Model model)
    {
        UserSearchForm searchForm = new UserSearchForm();
        model.addAttribute("searchCriteria", searchForm);
        return "user/search";

    }

    @RequestMapping(value ="/search", method = RequestMethod.POST)
    public String searchView(Model model, @ModelAttribute("searchCriteria") UserSearchForm searchForm)
    {
        List<User> users =  userService.searchUsers(searchForm);
        model.addAttribute("searchCriteria", searchForm);
        model.addAttribute("users", users);
        return "user/search";

    }

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

    public String delete(@PathVariable User user)
    {
        String name= user.getFirstname()+" "+user.getLastname();

        userService.delete(user);

        return "redirect:/";

    }





}
