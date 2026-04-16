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
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String createCat(@ModelAttribute("cat") Cat cat,
                            @RequestParam(value = "imageFile", required = false) MultipartFile file,
                            HttpSession session) throws Exception {

        Member loggedInMember = (Member) session.getAttribute("loggedInMember");
        cat.setOwner(loggedInMember);

        if (file != null && !file.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.write(filePath, file.getBytes());

            cat.setImage(fileName);

            System.out.println("Gemmer billede i: " + filePath);
        }

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
    public String updateCat(@ModelAttribute("cat") Cat cat,
                            @RequestParam(value = "imageFile", required = false) MultipartFile file) throws Exception {
        Cat existingCat = catService.getCatById(cat.getId());

        if (file != null && !file.isEmpty()) {

            String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.write(filePath, file.getBytes());

            cat.setImage(fileName);

        } else {
            // behold gammelt billede
            cat.setImage(existingCat.getImage());
        }


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
