package com.jacla.api.controllers;

import com.jacla.api.models.Dumpster;
import com.jacla.api.repositories.DumpsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.*;

import java.util.List;

@RestController
public class DumpsterController {

    @Autowired
    private final DumpsterRepository dumpsterRepo;
    DumpsterController(DumpsterRepository dumpsterRepo){
        this.dumpsterRepo=dumpsterRepo;
    }
    @GetMapping("/dumpster")
     public List<Dumpster> dumpsterList(@RequestParam(value = "name", defaultValue = "World") String name) {
        return dumpsterRepo.getDumpster();
    }

    @PostMapping("/dumpster")
    public List<Dumpster> newDumpster(@RequestBody Dumpster newDumpster) {
        return dumpsterRepo.postDumpster(newDumpster);
    }
}
