package com.example.racekatteklubben.Web;

import com.example.racekatteklubben.Application.CatService;
import com.example.racekatteklubben.Application.MemberService;
import com.example.racekatteklubben.Domain.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final CatService catService;

    public MemberController(MemberService memberService, CatService catService) {
        this.memberService = memberService;
        this.catService = catService;
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
        model.addAttribute("cats", catService.getCatsByOwner(loggedInMember));
        return "/members/home";
    }

    @PostMapping("/delete")
    public String deleteAccount(HttpSession session) {
        Member loggedInMember =
                (Member) session.getAttribute("loggedInMember");
        if (loggedInMember == null) {
            return "redirect:/members/login";
        }

        memberService.deleteMember(loggedInMember);
        session.invalidate();

        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute("member") Member member, Model model) {

        memberService.registerMember(member);
        return "redirect:/members/success";
    }

    @PostMapping("/login")
    public String loginMember(@RequestParam String email, @RequestParam String password, HttpSession session) {

        Member loggedInMember = memberService.login(email, password);

        session.setAttribute("loggedInMember", loggedInMember);

        return "redirect:/members/home";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/success")
    public String success() {
        return "/members/success";
    }

    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");
        if (loggedInMember == null) {
            return "redirect:/members/login";
        }

        model.addAttribute("loggedInMember", loggedInMember);
        model.addAttribute("members", memberService.getMembers());
        return "/members/list";
    }

    @GetMapping("/update")
    public String update(HttpSession session, Model model) {
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");
        if (loggedInMember == null) {
            return "redirect:/members/login";
        }

        model.addAttribute("loggedInMember", loggedInMember);
        return "/members/update";
    }

    @PostMapping("/update")
    public String update(HttpSession session, @ModelAttribute("loggedInMember") Member member, @RequestParam(required = false) String newPassword) {
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");
        member.setEmail(loggedInMember.getEmail());

        if (member.getName() == null || member.getName().trim().isEmpty()) {
            return "redirect:/members/update?error=Navn er påkrævet";
        }
        boolean updPass;
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            member.setPassword(newPassword);
            updPass = true;
        } else {
            member.setPassword(loggedInMember.getPassword());
            updPass = false;
        }

        memberService.updateMember(member, updPass);
        session.setAttribute("loggedInMember", member);
        return "redirect:/members/home";
    }
}
