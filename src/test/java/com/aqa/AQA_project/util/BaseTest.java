package com.aqa.AQA_project.util;

import com.aqa.AQA_project.AqaProjectApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    private static ConfigurableApplicationContext app;

    @BeforeSuite
    public void startServer() {
        app = SpringApplication.run(AqaProjectApplication.class);
    }
}