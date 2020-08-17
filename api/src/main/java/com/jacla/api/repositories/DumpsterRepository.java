package com.jacla.api.repositories;

import java.util.*;     
import com.jacla.api.models.Dumpster;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.*;

@Repository
public class DumpsterRepository  {

    private List<Dumpster> list = new ArrayList<Dumpster>();

    public List<Dumpster> getDumpster(){

        return list;
    }

    public List<Dumpster> postDumpster(Dumpster newDumpster){
        int count = 0; 
        boolean flag = true;
        if (list.size() == 0){
            list.add(newDumpster);
            
        }
        else{
            Dumpster change = newDumpster ;
            String ip1 = newDumpster.getIp();
            
            for (Dumpster odumpster: list){
                String ip2 = odumpster.getIp();
                if (ip1.equals(ip2)){
                    count+=1;
                    change = odumpster;
                }
            }
            if (count == 0){
                newDumpster.setId(list.size());
                list.add(newDumpster);
                
            }
            if(count ==1){
                change.setFilling(newDumpster.getFilling());
            }
        }
        return list;
    }
}
