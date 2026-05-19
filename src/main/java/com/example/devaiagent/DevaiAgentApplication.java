package com.example.devaiagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DevaiAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevaiAgentApplication.class, args);
    }

}
