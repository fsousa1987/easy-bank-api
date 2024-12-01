package com.francisco.easybankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.francisco.easybankapi.repository")
//@EntityScan(basePackages = "com.francisco.easybankapi.model")
public class EasyBankApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyBankApiApplication.class, args);
    }

}
