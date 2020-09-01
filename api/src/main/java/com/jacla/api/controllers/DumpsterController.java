package com.jacla.api.controllers;

import com.jacla.api.models.Dumpster;
import com.jacla.api.repositories.DumpsterRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bennes")
public class DumpsterController {

    @Autowired
    private DumpsterRepositoryImpl dumpsterRepository;

    @PostMapping
    public void saveDumpster(@RequestBody Dumpster dumpster){
        dumpsterRepository.save(dumpster);
    }

    @GetMapping
     public ResponseEntity<List<Dumpster>> dumpsterList() {
        List<Dumpster> dumpsterList = dumpsterRepository.findAll();
        return new ResponseEntity<List<Dumpster>>(dumpsterList, HttpStatus.OK);
    }
}
