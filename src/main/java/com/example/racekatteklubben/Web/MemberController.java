package com.example.racekatteklubben.Web;

import com.example.racekatteklubben.Application.MemberService;
import com.example.racekatteklubben.Application.Validation.ValidationException;
import com.example.racekatteklubben.Domain.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;


    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("member", new Member());
        return "/members/register";
    }


    @GetMapping("/login")
    public String login(){
        return "/members/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model){
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");

        if (loggedInMember == null) {
            return "redirect:/members/login";
        }
        model.addAttribute("loggedInMember", loggedInMember);
        return "/members/home";
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute("member") Member member, Model model) {

        memberService.registerMember(member);
        return "redirect:/members/success";
    }

    @PostMapping("/login")
    public String loginMember(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {

        Member loggedInMember = memberService.login(email, password);

        session.setAttribute("loggedInMember", loggedInMember);

        return "/members/home";

    }


    @GetMapping("/success")
    public String success() {
        return "/members/success";
    }
}
