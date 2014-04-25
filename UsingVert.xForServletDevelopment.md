# Using Vert.x for Servlet Development

***Java Servlets*** are components that run in a servlet container, and are frequently featured in Java Application Servers and are a cornerstone of Java EE. Java Servlets handle request coming from a Web browser and typicaly talk to a database to create interactive web application. As web application become richer and richer, the traditional web applicaiton are going to the wayside.

Java Servlets replaced Common Gateway Interface (CGI) dominance. Reactive programmign frameworks like Vert.x, Node.js, and their ilk are replacing Java Servlets and **Java EE**.

Reactive frameworks are to Java EE what NoSQL is to RDBMS and SQL.

## First Java Example


```java
public class Example extends Verticle {

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

        request.response().headers().add("Content-Type", "text/html; charset=UTF-8");
	    request.response().setStatusCode(200).end(

              "\n<html><body>Hi Mom!</body></html>\n"
        );

    }

}
```

## Running the first example

```
$ vertx run Example.java
```

[The full example is on github](./Example.java) in all its glory with a full metric ton of imports.


## Testing our first example

Open up a new terminal and run:

```
$ curl localhost:8080

<html><body>Hi Mom!</body></html>

```


