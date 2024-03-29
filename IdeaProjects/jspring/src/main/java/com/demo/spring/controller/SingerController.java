package com.demo.spring.controller;

import com.demo.spring.Service.SingerService;
import com.demo.spring.Service.UserService;
import com.demo.spring.domain.Singer;
import com.demo.spring.domain.SingerSearchForm;
import com.demo.spring.domain.User;
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
@RequestMapping(value = "/singer")
public class SingerController {
    @Autowired
    SingerService singerService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView(Model model)
    {
        Singer singer = new Singer();
    model.addAttribute("singer", singer);
        return "singers/register";

    }

    // Users can add a songname singer and add comments

    @RequestMapping(value = "/register", method = RequestMethod.POST)

    public String register(Model model, @Valid @ModelAttribute("singer") Singer singer, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("singer", singer);
            model.addAttribute("message", "Please fill in all Boxes.");
            return "singers/register";
        }

        singerService.save(singer);
        return "redirect:/";
    }

    // Validation is put in place to ensure all boxes are filled in

    @RequestMapping(value ="/update/{u}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable Singer u){

    model.addAttribute("singer", u);
    return "singers/update";

    }

    // CRUD, user can edit their post

    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("singer") Singer u){

        singerService.save(u);
       return "redirect:/";

    }

    // CRUD , Edited post is saved and user is re-directed to updated info on home page

    @RequestMapping(value ="/delete/{singer}", method =RequestMethod.GET)

    public String delete(@PathVariable Singer singer, Long id)
    {
    String name= singer.getSingername()+" "+singer.getSongname();

        singerService.delete(singer);

    return "redirect:/";

    }

    // CRUD, user is able to delete their post


    @RequestMapping(value ="/search", method = RequestMethod.GET)
    public String searchView(Model model)
    {
        SingerSearchForm searchForm = new SingerSearchForm();
        model.addAttribute("searchCriteria", searchForm);
        return "singers/search";

    }

    // User enters search result

    @RequestMapping(value ="/search", method = RequestMethod.POST)
    public String searchView(Model model, @ModelAttribute("searchCriteria") SingerSearchForm searchForm)
    {
        List<Singer> singers =  singerService.searchSingers(searchForm);
        model.addAttribute("searchCriteria", searchForm);
        model.addAttribute("singers", singers);
        return "singers/search";

    }
    // User posts search button and is displayed the results of the songs info


    @RequestMapping(value= "/data", method = RequestMethod.GET)
    public String graph(Model model, HttpSession session)
    {

        if(session.getAttribute("loginu")==null)
        {
            return "redirect:/user/loginu";
        }

        return "singers/data";

    }

    // Graph is displayed to the user


}



