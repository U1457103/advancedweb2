package com.demo.spring.controller;

import com.demo.spring.Service.SingerService;
import com.demo.spring.domain.Singer;
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
@RequestMapping(value = "/singer")
public class SingerController {
    @Autowired
    SingerService singerService;


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerView(Model model)
    {
        Singer singer = new Singer();
    model.addAttribute("singer", singer);
        return "register";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)

    public String register(Model model, @Valid @ModelAttribute("singer") Singer singer, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            model.addAttribute("singer", singer);
            model.addAttribute("message", "Please fill in all Boxes.");
            return "register";
        }

        singerService.save(singer);
        return "redirect:/";
    }

    @RequestMapping(value ="/update/{u}", method = RequestMethod.GET)
    public String updateView(Model model, @PathVariable Singer u){

    model.addAttribute("singer", u);
    return "update";

    }

    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("singer") Singer u){

        singerService.save(u);
       return "redirect:/";

    }

    @RequestMapping(value ="/delete/{singer}", method =RequestMethod.GET)

    public String delete(@PathVariable Singer singer)
    {
    String name= singer.getSingername()+" "+singer.getSongname();

        singerService.delete(singer);

    return "redirect:/";

    }





}
