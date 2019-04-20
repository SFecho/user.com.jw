package com.lnnu.studyhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class StudyhelperApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StudyhelperApplication.class, args);
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource.getClass());
    }
}
