package com.example.racekatteklubben.Web;

import com.example.racekatteklubben.Application.CatService;
import com.example.racekatteklubben.Domain.Cat;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cats")
public class CatController {

    private final CatService catService;
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cat", new Cat());
        return "cats/create";
    }

    @PostMapping("/create")
    public String CreateCat(@ModelAttribute("cat") Cat cat, Model model) {

        try {
            catService.addCat(cat);
            return "redirect:/cats/success";

        }catch(Exception e) {
            model.addAttribute("errMessage", e.getMessage());
            return "cats/create";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Cat cat = catService.getCatById(id);
        model.addAttribute("cat", cat);
        return "cats/update";
    }

    @GetMapping("/success")
    public String success() {
        return "cats/success";
    }

    @GetMapping("/catOwner")
    public String catOwner(@ModelAttribute("cat") Cat cat , Model model, HttpSession session) {
        model.addAttribute("cat", new Cat());
        return "cats/catOwner";
    }
}
