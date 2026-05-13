package br.com.emakers.psemakers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PsEmakersApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsEmakersApplication.class, args);
    }

}
