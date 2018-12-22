package com.example.demo.controller;
//===================================================PASS==============================================
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class BakeryTypeController {

    @Autowired private BakeryTypeRepository bakerytypeRepository;

    @GetMapping(path = "/bakerytype")
    private Collection<BakeryType> getBakerytpeCollection() {
        return this.bakerytypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/bakerytype/{id}")
    private BakeryTypeRepository getBakeryTypeById(@PathVariable Long id){
        return (BakeryTypeRepository) this.bakerytypeRepository.findById(id).get();
    }
    @PutMapping(path = "/bakerytype/{id}")
    private BakeryType replaceBakeryType(@RequestBody BakeryType newBakeryType, @PathVariable Long id){
        return bakerytypeRepository.findById(id).map(bakerytype -> {
            bakerytype.setBakery(newBakeryType.getBakery());
            return bakerytypeRepository.save(bakerytype);
        }).orElseGet(() -> {
            newBakeryType.setId(id);
            return bakerytypeRepository.save(newBakeryType);
        });
    }
    @PostMapping(path = "/bakerytype")
    private BakeryType newBakeryType(@RequestBody BakeryType newBakeryType){
        return bakerytypeRepository.save(newBakeryType);
    }

    @DeleteMapping(path = "/bakerytype/{bkid}")
    private void deleteAById(@PathVariable Long bkid){
        bakerytypeRepository.deleteById(bkid);
    }
}
