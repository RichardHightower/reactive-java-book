It is early days for this, but I figure someone might find it useful if you are doing load testing with vertx. 

This is a very basic example. There might be better example later on. Gatling is a load testing tool similar to JMeter and Grinder. 

(I have been writing my own so I find Gatling very nice.) Gatling uses Netty and Akka and is written in Scala (but I like it anyway).

The template is based on the gradle-vertx template. I just added the ability to run gatling (scala based) tests from the vertx gradle build template.

The Gatling and Gradle Example based on this blog post about Load Testing in Gradle with Gatling, except I needed Gatling 2.0.

I also stripped out all of the JS, Groovy, Ruby stuff so if you want a cleaner Vertx Gradle template it might help (unless of course you are not using Java then it will not help at all). I plan on writing more templates. Let's see if I follow through. I might.


# Vert.x Gradle Gatling Vertx Template

Template for creating a Java Vert.x module with a Gradle build.

This sample also uses gatling for laod testing.

It might not be a bad environment to learn gatling and vertx.


So for anyone else who would like to use this gradle script with gatling 2.0 snapshot, here are some instructions. (I am assuming you are not a Scala pro like myself.)


1) Edit sbt and add 8x more memory or you get OutOfMemoryExceptions

```
$ emacs /usr/local/Cellar/sbt/0.13.1/bin/sbt
```


Add a lot of memory to SBT!

```
#!/bin/sh                                                                                                                       

test -f ~/.sbtconfig && . ~/.sbtconfig

exec java -Xmx6g ${SBT_OPTS} -jar /usr/local/Cellar/sbt/0.13.1/libexec/sbt-launch.jar "$@"
```

2) Build Gatling and highcharts

```
$ git pull https://github.com/excilys/gatling.git
$ sbt publishM2
```

```
$ git pull https://github.com/excilys/gatling-highcharts.git
$ sbt publishM2
```

I guess use publishLocal if you are using Ivy or you are already a gradle shop who uses Ivy repos. My clients mostly use maven so publishM2 puts things where the maven projects can find them. I suppose SBT and gradle shops arlready use ivy repos so..... IDK.


You can find the example here:


https://github.com/RichardHightower/reactive-java-book (I don't know if this "book" will ever get done... I started it last week (4/2014), but it where I am putting my notes for vertx, hazelcast, boon, websocket and slumberdb, docker, microservice stuff that I am working on, and now gatling examples. It is nascent so not much there yet and maybe never).

To use the example

```
$ git pull https://github.com/RichardHightower/reactive-java-book.git

$ cd reactive-java-book/examples/project-starter-with-load-test/
```


Runs the sample verticle (horrible, it will be one task at some point)

```
$ ./gradlew clean compileJava modZip install runMod
```


Run gatling

```
 $ ./gradlew clean  load
```


Gatling will print out where the reports are located. 

Open with Chrome or Safari, do not use FireFox, unless you want to. :)



Ok... now I am going to go learn Gatling.



Sample project

I have a vertx verticle (more or less like a servlet).

```java

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
```


The Gatling test is here:

```scala

package com.example

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.HeaderNames._
import io.gatling.http.HeaderValues._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.http.HeaderNames._
import io.gatling.http.HeaderValues._

import scala.concurrent.duration._

class ExampleVerticleLoadTest extends Simulation {

  val httpProtocol = http
    .baseURL(System.getProperty("HOST_UNDER_TEST", "http://localhost:8080/"))

  val scn = scenario("ExampleVerticle")
    .exec(http("Get Hello World").get("/"))


  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
```

So basically... Hello World.




BTW, that graph looks beautiful. I can't wait until I run some real tests.

