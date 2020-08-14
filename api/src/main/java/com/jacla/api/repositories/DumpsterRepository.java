package com.jacla.api.repositories;

import com.jacla.api.models.Dumpster;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DumpsterRepository {

    public List<Dumpster> getDumpster(){
        Dumpster d1 = new Dumpster("Gab", 54,true,"192.168.5");
        Dumpster d2 = new Dumpster("Theo", 14,true,"192.168.6");
        List<Dumpster> list = new ArrayList<Dumpster>();
        list.add(d1);
        list.add(d2);
        return list;
    }
}
