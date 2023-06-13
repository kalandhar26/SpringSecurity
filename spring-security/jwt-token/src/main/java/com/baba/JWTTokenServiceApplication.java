package com.baba;

import com.baba.Models.RegisterRequest;
import com.baba.services.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.baba.entities.Role.ADMIN;
import static com.baba.entities.Role.USER;

@SpringBootApplication
public class JWTTokenServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JWTTokenServiceApplication.class);
    }

    // Add Admin and User
    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service){

        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("baba")
                    .lastname("kalandhar")
                    .email("admin")
                    .password("admin")
                    .role(ADMIN)
                    .build();
            System.out.println(" Admin Token: "+service.register(admin).getToken());

            var user = RegisterRequest.builder()
                    .firstname("baba")
                    .lastname("kalandhar")
                    .email("user")
                    .password("user")
                    .role(USER)
                    .build();
            System.out.println(" User Token: "+service.register(user).getToken());


        };

    }
}
