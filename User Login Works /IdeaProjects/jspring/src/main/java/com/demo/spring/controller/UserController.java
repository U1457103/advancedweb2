package com.demo.spring.controller;


import com.demo.spring.Service.UserService;
import com.demo.spring.domain.LoginForm;
import com.demo.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return "registeru";

    }

    @RequestMapping(value = "/registeru", method = RequestMethod.POST)

    public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("message", "Please fill in all Boxes.");
            return "registeru";
        }

        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/loginu", method = RequestMethod.GET)
    public String loginView(Model model)
    {
        LoginForm user = new LoginForm();
        model.addAttribute("user", user);
        return "loginu";

    }

    @RequestMapping(value = "/loginu", method = RequestMethod.POST)

    public String login(Model model, @Valid @ModelAttribute("user") LoginForm user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("message", "Please fill in all Boxes.");
            return "loginu";
        }
        if (userService.validateLogin(user)==null || userService.validateLogin(user).size()==0)
        {
            model.addAttribute("user", user);
            model.addAttribute("message", "Incorrect Details.");
            return "loginu";
        }

        return "redirect:/";
    }


    @RequestMapping(value ="/updateu/{u}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable User u){

        model.addAttribute("user", u);
        return "updateu";

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
