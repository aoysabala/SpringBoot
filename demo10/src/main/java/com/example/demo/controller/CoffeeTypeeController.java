package com.example.demo.controller;
//===================================================PASS==============================================
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class CoffeeTypeeController {
    @Autowired private CoffeeTypeRepository coffeetypeRepository;

    @GetMapping(path = "/coffeetype")
    private Collection<CoffeeType> getCoffeeTypeCollection(){
        return this.coffeetypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/coffeetype/{id}")
    private CoffeeTypeRepository getCoffeeTypeById(@PathVariable Long id){
        return (CoffeeTypeRepository) this.coffeetypeRepository.findById(id).get();
    }

    @PutMapping(path = "/coffeetype/{id}")
    private CoffeeType replaceCoffeeTypee(@RequestBody CoffeeType newCoffeeType, @PathVariable Long id){
        return coffeetypeRepository.findById(id).map(coffeetype -> {
            coffeetype.setCoffee(newCoffeeType.getCoffee());
            return coffeetypeRepository.save(coffeetype);
        }).orElseGet(() -> {
            newCoffeeType.setId(id);
            return coffeetypeRepository.save(newCoffeeType);
        });
    }

    @PostMapping(path = "/coffeetype")
    private CoffeeType newCoffeeType(@RequestBody CoffeeType newCoffeeType){
        return coffeetypeRepository.save(newCoffeeType);
    }

    @DeleteMapping(path = "/coffeetype/{cfid}")
    private void deleteAById(@PathVariable Long gdid){
        coffeetypeRepository.deleteById(gdid);
    }
}