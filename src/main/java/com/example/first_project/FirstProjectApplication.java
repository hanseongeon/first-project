package com.example.first_project;

import com.example.first_project.Global.OSType;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstProjectApplication {
    @Getter
    private static OSType osType;

    public static void main(String[] args) {
        osType = OSType.getInstance();
        if (osType != null) SpringApplication.run(FirstProjectApplication.class, args);
    }

}
