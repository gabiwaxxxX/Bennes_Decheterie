package com.jacla.api.controllers;

import com.jacla.api.models.Dumpster;
import com.jacla.api.repositories.DumpsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DumpsterController {

    @Autowired
    private final DumpsterRepository dumpsterRepo = new DumpsterRepository();

    @GetMapping("/dumpster")
    public List<Dumpster> dumpsterList(@RequestParam(value = "name", defaultValue = "World") String name) {
        return dumpsterRepo.getDumpster();
    }
}
