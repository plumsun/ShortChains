package com.example.short_chains;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Short chains application.
 *
 * @author plumsun Created on 2023-12-03
 */
@SpringBootApplication(exclude = {
//        RedissonAutoConfigurationV2.class
})
@MapperScan("com.example.short_chains.repository")
public class ShortChainsApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ShortChainsApplication.class, args);
    }

}
