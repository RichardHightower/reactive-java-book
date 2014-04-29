package com.example.test.integration;


import org.junit.Test;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClientResponse;
import org.vertx.testtools.TestVerticle;

import static org.vertx.testtools.VertxAssert.*;

public class ExampleIntegrationTest extends TestVerticle {

  @Test
  public void testHTTP() {
        vertx.createHttpClient().setPort(8080).getNow("/",new Handler<HttpClientResponse>() {
          @Override
          public void handle(HttpClientResponse resp) {

              final Buffer body = new Buffer(0);

              resp.dataHandler(new Handler<Buffer>() {
                  public void handle(Buffer buffer) {
                      body.appendBuffer(buffer);
                  }
              });
              resp.endHandler(new VoidHandler() {
                  public void handle() {

                      String message = body.toString();
                      assertTrue(message.contains("Hi Mom!"));
                  }
              });

             testComplete();
          }
        });
  }



    @Override
    public void start() {
        initialize();
        container.deployModule(System.getProperty("vertx.modulename"), new AsyncResultHandler<String>() {
            @Override
            public void handle(AsyncResult<String> asyncResult) {
                // Deployment is asynchronous and this this handler will be called when it's complete (or failed)
                if (asyncResult.failed()) {
                    container.logger().error(asyncResult.cause());
                }
                assertTrue(asyncResult.succeeded());
                assertNotNull("deploymentID should not be null", asyncResult.result());
                // If deployed correctly then start the tests!
                startTests();
            }
        });
    }



}
