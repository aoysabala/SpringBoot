package com.example.demo.controller;
//===================================================PASS==============================================
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

import  java.util.Collection;
import java.util.stream.Collectors;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
class ManuController {
    private ManuRepository repository;

    public ManuController(ManuRepository manuRepository, CoffeeTypeRepository coffeetypeRepository,
                          BakeryTypeRepository bakerytypeRepository,
                          ManuTypeRepository manutypeRepository) {
        this.repository = repository;
    }

    @Autowired
    private ManuRepository manuRepository;
    @Autowired
    private ManuTypeRepository manutypeRepository;
    @Autowired
    private CoffeeTypeRepository coffeetypeRepository;
    @Autowired
    private BakeryTypeRepository bakerytypeRepository;

    @GetMapping("/manu")
    public Collection<Manu> menu() {
        return manuRepository.findAll().stream()
                .filter(this::isManu)
                .collect(Collectors.toList());
    }

    private boolean isManu(Manu manu) {
        return !manu.getName().equals("no no!");
    }
    @GetMapping(path = "/Manu")
    public Collection<Manu> menuRepository() {
        return manuRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/Manu/{manuId}")
    private ManuRepository getManuById(@PathVariable Long id){
        return (ManuRepository) this.manuRepository.findById(id).get();
    }

    @GetMapping(path = "/ManuType")
    public Collection<ManuType> manutypesRepository() {
        return manutypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/CoffeeType")
    public Collection<CoffeeType> coffeetypeRepository() {
        return coffeetypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/BakeryType")
    public Collection<BakeryType> bakerytypeRepository() {
        return bakerytypeRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping(path = "/manu/{name}/{price}/{manutype}/{coffeetype}/{bakerytype}")
    public Manu Manu(@PathVariable String name, @PathVariable int price
            , @PathVariable Long manutype, @PathVariable Long coffeetype,
                     @PathVariable Long bakerytype) {

        Manu manu = new Manu();
        ManuType manutype1 = manutypeRepository.findById(manutype).get();
        CoffeeType coffeetype1 = coffeetypeRepository.findById(coffeetype).get();
        BakeryType bakerytype1 = bakerytypeRepository.findById(bakerytype).get();

        manu.setManutype(manutype1);
        manu.setCoffeetype(coffeetype1);
        manu.setBakerytype(bakerytype1);
        manu.setName(name);
        manu.setPrice(price);
        manuRepository.save(manu);

        return manu;

    }

}