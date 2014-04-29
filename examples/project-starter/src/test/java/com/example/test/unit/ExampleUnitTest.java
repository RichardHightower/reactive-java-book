package com.example.test.unit;

import com.example.ExampleVerticle;
import org.junit.Test;
import org.junit.Assert;


public class ExampleUnitTest {

  public static final String HELLO_WORLD = "Hello World!";

  @Test
  public void testVerticle() {

      ExampleVerticle verticle = new ExampleVerticle();
      verticle.setMessage(HELLO_WORLD);

      Assert.assertEquals(HELLO_WORLD, verticle.getMessage());

  }
}
