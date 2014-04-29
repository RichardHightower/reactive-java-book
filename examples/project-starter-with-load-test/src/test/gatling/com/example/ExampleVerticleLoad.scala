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