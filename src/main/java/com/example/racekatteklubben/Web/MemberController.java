package com.example.racekatteklubben.Web;

import com.example.racekatteklubben.Application.MemberService;
import com.example.racekatteklubben.Application.Validation.ValidationException;
import com.example.racekatteklubben.Domain.Member;
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
        return "members/register";
    }

    @GetMapping("login")
    public String login(){
        return "members/login";
    }

    @PostMapping("/register")
    public String registerMember(
            @ModelAttribute("member") Member member,
            Model model) {

        try {
            memberService.registerMember(member);
            return "redirect:/members/success";

        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register-member";
        }
    }

    @PostMapping("/login")
    public String loginMember(@RequestParam String email, @RequestParam String passwordHash, Model model) {
        try {
            memberService.login(email, passwordHash);
            return "members/home";
        } catch (ValidationException e) {
            model.addAttribute("error", e.getMessage());
            return "members/login";
        }
    }


    @GetMapping("/success")
    public String success() {
        return "members/success";
    }
}
