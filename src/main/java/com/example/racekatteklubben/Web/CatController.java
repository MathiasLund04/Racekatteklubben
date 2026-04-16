package com.example.racekatteklubben.Web;

import com.example.racekatteklubben.Application.CatService;
import com.example.racekatteklubben.Application.MemberService;
import com.example.racekatteklubben.Domain.Cat;
import com.example.racekatteklubben.Domain.Member;
import com.example.racekatteklubben.Domain.Gender;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/cats")
public class CatController {

    private final CatService catService;
    private final MemberService memberService;
    public CatController(CatService catService, MemberService memberService) {
        this.catService = catService;
        this.memberService = memberService;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cat", new Cat());
        model.addAttribute("genders", Gender.values());
        return "cats/create";
    }

    @PostMapping("/create")
    public String CreateCat(@ModelAttribute("cat") Cat cat,HttpSession session) {
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");
        cat.setOwner(loggedInMember);
        catService.addCat(cat);
        return "redirect:/cats/catSuccess";

    }

    @GetMapping("/catProfile/{id}")
    public String catProfile(@PathVariable int id, Model model) {
        Cat cat = catService.getCatById(id);
        model.addAttribute("cat", cat);
        return "/cats/catProfile";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Cat cat = catService.getCatById(id);
        model.addAttribute("cat", cat);
        model.addAttribute("genders", Gender.values());
        return "cats/update";
    }

    @PostMapping("/update")
    public String updateCat(@ModelAttribute("cat") Cat cat) {
        catService.updateCat(cat.getId(), cat);
        return "redirect:/cats/catSuccess";
    }


    @GetMapping("/catSuccess")
    public String success() {
        return "cats/catSuccess";
    }

    @GetMapping("/{owner}")
    public String getCatsByOwner(@PathVariable("owner") String email, HttpSession session, Model model) {
        Member loggedInMember =
                (Member) session.getAttribute("loggedInMember");

        if (loggedInMember == null) {
            return "redirect:/members/login";
        }
        Member owner = memberService.getMemberByEmail(email);
        List<Cat> cats = catService.getCatsByOwner(owner);
        model.addAttribute("cats", cats);
        model.addAttribute("member", owner);

        return "cats/list";
    }

    @GetMapping("/catOwner")
    public String catOwner( Model model, HttpSession session) {
        Member loggedInMember = (Member) session.getAttribute("loggedInMember");

        model.addAttribute("loggedInMember", loggedInMember);
        model.addAttribute("cats", catService.getCatsByOwner(loggedInMember));

        return "cats/catOwner";
    }

    @GetMapping("/deleteConfirmation/{id}")
    public String deleteCat(@PathVariable int id, Model model) {
    Cat cat = catService.getCatById(id);
    model.addAttribute("cat", cat);
    return "cats/deleteConfirmation";
    }

    @PostMapping("/deleteConfirmation/{id}")
    public String deleteCat(@PathVariable int id) {
        catService.removeCat(id);
        return "redirect:/members/home";

    }


}
