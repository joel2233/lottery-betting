package pers.joel;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class LotteryBettingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LotteryBettingApplication.class, args);
    }

    @Bean
    VelocityEngine velocityEngine() throws IOException{
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/velocity.properties"));
        return new VelocityEngine(properties);
    }
}
