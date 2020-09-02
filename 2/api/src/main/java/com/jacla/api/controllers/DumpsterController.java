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
    public void postDumpster(@RequestBody Dumpster dumpster){
        dumpsterRepository.post(dumpster);
    }

    @GetMapping("/{id}")
    public Dumpster getDumpster(@PathVariable(value = "id") String id) {
        return dumpsterRepository.get(id);
    }

    @GetMapping
     public ResponseEntity<List<Dumpster>> getDumpsters() {
        List<Dumpster> dumpsterList = dumpsterRepository.getAll();
        return new ResponseEntity<List<Dumpster>>(dumpsterList, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public long deleteDumpster(@PathVariable(value="id") String id){
        return dumpsterRepository.delete(id);
    }

    @PutMapping
    public void updateDumpster(@RequestBody Dumpster dumpster){
        dumpsterRepository.update(dumpster);
    }

}
