package cn.edu.scau.DataCollectionSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class View {

    @RequestMapping(value="/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value="/admin")
    public String admin(){
        return "admin";
    }
}
