package org.dsgoers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FantasyFootballApplication {

  public static void main(String[] args) {
    SpringApplication.run(FantasyFootballApplication.class, args);
  }

  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
    return factory -> factory.setContextPath("/fantasy-football");
  }
}
