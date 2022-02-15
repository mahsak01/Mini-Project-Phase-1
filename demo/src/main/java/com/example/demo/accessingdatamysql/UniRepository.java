package com.example.demo.accessingdatamysql;

import com.example.demo.Models.Uni;
import org.springframework.data.repository.CrudRepository;


public interface UniRepository extends CrudRepository<Uni, Long> {

    Uni findByUniName(String uniName);

}