package com.zs.commandev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Commandev2Application {

    public static ConfigurableApplicationContext ctx;
    public static void main(String[] args) {
         ctx= SpringApplication.run(Commandev2Application.class, args);

    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }


}
