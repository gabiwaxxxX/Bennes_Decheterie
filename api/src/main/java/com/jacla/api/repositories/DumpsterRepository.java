package com.jacla.api.repositories;

import java.util.*;     
import com.jacla.api.models.Dumpster;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DumpsterRepository {
    void save(Dumpster dumpster);
    Dumpster find(String id);
    List<Dumpster> findAll();

}
