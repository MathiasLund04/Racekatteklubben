package com.example.racekatteklubben.Application;

import com.example.racekatteklubben.Application.Validation.Validation;
import com.example.racekatteklubben.Domain.Cat;
import com.example.racekatteklubben.Domain.Member;
import com.example.racekatteklubben.Domain.ICatRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class CatService {
    private ICatRepository cRepo;
    private Validation validation;

    public CatService(ICatRepository cRepo, Validation validation) {
        this.cRepo = cRepo;
        this.validation = validation;
    }

    public void addCat(Cat cat, MultipartFile file) throws IOException {
        validation.validateCat(cat);
        addImage(cat, file);
        cRepo.addCat(cat);
    }

    public void updateCat(int id, Cat cat,  MultipartFile file) throws IOException {
        validation.validateCat(cat);
        addImage(cat, file);
        cRepo.updateCat(id, cat);

    }

    public void removeCat(int id){
        validation.validateInt(id);
        cRepo.removeCat(id);
    }

    public Cat getCatById(int id){
        validation.validateInt(id);
        return cRepo.getCatById(id);
    }

    public List<Cat> getCatsByOwner(Member owner){
        return cRepo.getCatsByOwner(owner);
    }

    public List<Cat> getCatsByBreed(String breed){
        validation.validateString(breed);
        return cRepo.getCatsByBreed(breed);
    }

    public List<Cat> getCatsByFather(String father){
        validation.validateString(father);
        return cRepo.getCatsByFather(father);
    }

    public List<Cat> getCatsByMother(String mother){
        validation.validateString(mother);
        return cRepo.getCatsByMother(mother);
    }

    private void addImage(Cat cat, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String uploadDir = "uploads/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.write(filePath, file.getBytes());
            cat.setImage(fileName);
        }

    }



}
