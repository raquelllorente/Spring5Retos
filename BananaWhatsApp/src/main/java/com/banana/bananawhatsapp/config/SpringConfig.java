package com.banana.bananawhatsapp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-prod.properties")
@PropertySource("classpath:application-dev.properties")
@ComponentScan(basePackages = {"com.banana.bananawhatsapp.persistencia", "com.banana.bananawhatsapp.servicios"})
@EntityScan("com.banana.bananawhatsapp.modelos")
@EnableJpaRepositories(basePackages = {"com.banana.bananawhatsapp.persistencia"})
@EnableAutoConfiguration
public class SpringConfig {
}
