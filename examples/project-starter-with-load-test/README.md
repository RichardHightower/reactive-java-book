# Vert.x Gradle Gatling Template

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



Ok... now I am going to go learn gatling.



BTW, that graph looks beautiful. I can't wait until I run some real tests.

