package com.demo.spring.controller;

import com.demo.spring.Service.SingerService;
import com.demo.spring.domain.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by web on 17/04/17.
 */
@Controller
public class HomeController {

    @Autowired
    SingerService singerService;


    @RequestMapping(value= "/", method = RequestMethod.GET)
   // @ResponseBody
    public String index(Model model)
    {

    List<Singer> singers = singerService.findAll();
        model.addAttribute("singers", singers);

        return "index";

    }

}
