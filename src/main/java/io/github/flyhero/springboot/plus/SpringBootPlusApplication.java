package io.github.flyhero.springboot.plus;

import io.github.flyhero.springboot.plus.generator.AbstractGenerator;
import io.github.flyhero.springboot.plus.generator.GeneratorRegister;
import io.github.flyhero.springboot.plus.config.PlusConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPlusApplication.class, args);
    }

}
