package org.dsgoers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FantasyFootballTestConfiguration {

  @Bean
  public String baseUrl() {
    return "helloworld";
  }
}
