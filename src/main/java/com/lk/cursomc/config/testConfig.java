package com.lk.cursomc.config;

import com.lk.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class testConfig {

    @Autowired
    private DBService _dbService;


    @Bean
    public Boolean instantiateDatabase() throws ParseException {
        _dbService.instantiateTestDatabase();
        return true;
    }

}
