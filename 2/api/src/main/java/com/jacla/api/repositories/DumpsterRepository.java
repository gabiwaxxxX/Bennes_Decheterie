package com.jacla.api.repositories;

import java.util.*;     
import com.jacla.api.models.Dumpster;
import org.springframework.stereotype.Repository;

@Repository
public interface DumpsterRepository {
    void post(Dumpster dumpster);
    Dumpster get(String id);
    List<Dumpster> getAll();
    long delete(String id);
    void update(Dumpster dumpster);

}
