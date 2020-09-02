package com.jacla;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.repository.CrudRepository;


@RepositoryRestResource(collectionResourceRel = "benne", path = "benne")
public interface BenneRepository extends PagingAndSortingRepository<Benne, Long> {

  List<Benne> findByNomBenne(@Param("nomBenne") String nomBenne);

}
