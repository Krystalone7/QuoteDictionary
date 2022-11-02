package com.artyom.quotedictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class QuoteDictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteDictionaryApplication.class, args);
    }

}
