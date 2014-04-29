package com.example;


import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

public class ExampleVerticle extends Verticle {

    private final String CONTENT_TYPE_KEY = "Content-Type";
    private final String CONTENT_TYPE_VALUE = "text/html; charset=UTF-8";
    private String message = "\n<html><body>Hi Mom!</body></html>\n";

    public void start() {

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(new Handler<HttpServerRequest>() {
            public void handle(final HttpServerRequest request) {
                doGet(request);
            }
        });
        server.listen(8080);

    }

    void doGet(HttpServerRequest request) {
        request.response().headers().add(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
        request.response().setStatusCode(200).end(
                this.message
        );
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}