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
public class ManuTypeController {
    @Autowired
    private ManuTypeRepository manutypeRepository;

    @GetMapping(path = "/manutype")
    private Collection<ManuType> getManuTypeCollection() {
        return this.manutypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping(path = "/manutype/{id}")
    private ManuTypeRepository getManuTypeById(@PathVariable Long id) {
        return (ManuTypeRepository) this.manutypeRepository.findById(id).get();
    }

    @PutMapping(path = "/manutype/{id}")
    private ManuType replaceManuType(@RequestBody ManuType newManuType, @PathVariable Long id) {
        return manutypeRepository.findById(id).map(manutype -> {
            manutype.setManu(newManuType.getManu());
            return manutypeRepository.save(manutype);
        }).orElseGet(() -> {
            newManuType.setId(id);
            return manutypeRepository.save(newManuType);
        });
    }

    @PostMapping(path = "/manutype")
    private ManuType newManuType(@RequestBody ManuType newManuType) {
        return manutypeRepository.save(newManuType);
    }

    @DeleteMapping(path = "/manutype/{id}")
    private void deleteAById(@PathVariable Long id) {
        manutypeRepository.deleteById(id);
    }
}