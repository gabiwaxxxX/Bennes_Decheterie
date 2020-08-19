package com.jacla.api.repositories;

import java.util.*;     
import com.jacla.api.models.Dumpster;
import org.springframework.stereotype.Repository;

@Repository
public class DumpsterRepository  {

    private List<Dumpster> list = new ArrayList<Dumpster>();

    public DumpsterRepository(){
        list.add(new Dumpster("Theo", 35,true,"192.168.12"));
        list.add(new Dumpster("Gab", 66,true,"192.168.13"));
        list.add(new Dumpster("Gaetan", 13,true,"192.168.14"));
        list.add(new Dumpster("Tutu", 85,true,"192.168.15"));
        System.out.println(list);
    }

    public List<Dumpster> getDumpsters(){
        return list;
    }

    public Object[][] getObject(){
        Object objectList[][] = new Object[list.size()][];
        int i=0;
        for(Dumpster d : list){
            objectList[i] = new Object[]{d.getName(), d.getFilling(), d.getConnection(),d.getIp()};
            i++;
        }
        return objectList;
    }

    /*public List<Dumpster> addDumpster(Dumpster newDumpster){
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

            if(count ==1){
                change.setFilling(newDumpster.getFilling());
            }
        }
        return list;
    }*/
}
