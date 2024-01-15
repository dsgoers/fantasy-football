package org.dsgoers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class FantasyFootballApplicationTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void startsSuccessfully() {
    assertNotNull(applicationContext);
  }
}
