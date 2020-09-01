package com.jacla.api.repositories;

import com.jacla.api.models.Dumpster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DumpsterRepositoryImpl implements DumpsterRepository {
    private static final String TABLE_NAME = "Dumpster";
    private RedisTemplate<String, Dumpster> redisTemplate;
    private HashOperations<String, String, Dumpster> hashOperations;

    @Autowired
    public DumpsterRepositoryImpl(RedisTemplate<String, Dumpster> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void initializeHashOperations(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Dumpster dumpster) {
        hashOperations.put(TABLE_NAME,dumpster.getId(),dumpster);
    }

    @Override
    public Dumpster find(String id) {
        return hashOperations.get(TABLE_NAME,id);
    }

    @Override
    public List<Dumpster> findAll() {
        List<Dumpster> dumpsterList = new ArrayList<Dumpster>();
        hashOperations.entries(TABLE_NAME).forEach((key, element)-> dumpsterList.add(element));
        return dumpsterList;
    }
}
