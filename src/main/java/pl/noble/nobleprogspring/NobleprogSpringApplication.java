package pl.noble.nobleprogspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;

import java.rmi.server.UID;

@SpringBootApplication
public class NobleprogSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(NobleprogSpringApplication.class, args);
    }

    @Bean
    public String appName(){
        return "Sprint Boot App";
    }

}
