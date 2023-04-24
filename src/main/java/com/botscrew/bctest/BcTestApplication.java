package com.botscrew.bctest;

import com.botscrew.bctest.console.UniversityCL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@SpringBootApplication
public class BcTestApplication implements CommandLineRunner {

    @Autowired
    private UniversityCL universityCL;
    public static void main(String[] args) {
        SpringApplication.run(BcTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        universityCL.run();
    }
}
