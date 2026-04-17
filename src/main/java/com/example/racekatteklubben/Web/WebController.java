package com.example.racekatteklubben.Web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/index")
    public String index(HttpSession session){
        session.invalidate();
        return "/index";
    }

}