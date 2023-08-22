package com.workintech.fswebs17d2.rest;

import com.workintech.fswebs17d2.model.JuniorDeveloper;
import com.workintech.fswebs17d2.model.MidDeveloper;
import com.workintech.fswebs17d2.tax.DeveloperTax;
import jakarta.annotation.PostConstruct;
import com.workintech.fswebs17d2.model.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.workintech.fswebs17d2.tax.Taxable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    private Map<Integer, Developer> developers;
    private Taxable taxable;

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
    }

    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @GetMapping("/")
    public List<Developer> getAll() {
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer getDeveloper(@PathVariable int id) {
        return developers.get(id);
    }

    @PostMapping("/")
    public Developer insertDeveloper(@RequestBody Developer developer) {

        Developer newDeveloper = null ;

        switch (developer.getExperience()) {

            case JUNIOR:
                newDeveloper = new Developer(developer.getId(),developer.getName(),
                        (int) (developer.getSalary() - developer.getSalary()*taxable.getSimpleTaxRate()),
                        developer.getExperience());
                break;
            case MID:
                newDeveloper = new Developer(developer.getId(),developer.getName(),
                        (int) (developer.getSalary() - developer.getSalary()*taxable.getMiddleTaxRate()),
                        developer.getExperience());
                break;
            case SENIOR:
                newDeveloper = new Developer(developer.getId(),developer.getName(),
                        (int) (developer.getSalary() - developer.getSalary()*taxable.getUpperTaxRate()),
                        developer.getExperience());
                break;
        }

        developers.put(developer.getId(), newDeveloper);
        return newDeveloper;
    }

    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable int id, @RequestBody Developer developer) {
        Developer updatedDeveloper = null ;

        switch (developer.getExperience()) {

            case JUNIOR:
                updatedDeveloper = new Developer(developer.getId(),developer.getName(),
                        (int) (developer.getSalary() - developer.getSalary()*taxable.getSimpleTaxRate()),
                        developer.getExperience());
                break;
            case MID:
                updatedDeveloper = new Developer(developer.getId(),developer.getName(),
                        (int) (developer.getSalary() - developer.getSalary()*taxable.getMiddleTaxRate()),
                        developer.getExperience());
                break;
            case SENIOR:
                updatedDeveloper = new Developer(developer.getId(),developer.getName(),
                        (int) (developer.getSalary() - developer.getSalary()*taxable.getUpperTaxRate()),
                        developer.getExperience());
                break;
        }

        developers.put(id, updatedDeveloper);
        return updatedDeveloper;
    }
    @DeleteMapping("/{id}")
    public Developer removeDeveloper(@PathVariable int id) {
        Developer removedDeveloper = developers.get(id);
        developers.remove(id);
        return removedDeveloper;
    }
}
