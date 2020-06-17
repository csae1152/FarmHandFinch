# FarmHandFinch
Building Scala HTTPS API's with Finagle, Finch and Thrift.

From the Finagle documentation at https://finagle.github.io/finch/

The following example creates an HTTP server (powered by Finagle) that serves the only endpoint POST /time. This endpoint takes a Locale instance represented as JSON in request body and returns a current Time for this locale.

build.sbt:

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "9000",
  "com.github.finagle" %% "finch-circe" % "0.1.0",
  "io.circe" %% "circe-generic" % "0.9.0"
)
Main.scala:

import com.twitter.finagle.Http
import com.twitter.util.Await

import io.finch._
import io.finch.circe._
import io.finch.syntax._
import io.circe.generic.auto._

object Main extends App {

  case class Locale(language: String, country: String)
  case class Time(locale: Locale, time: String)

  def currentTime(l: java.util.Locale): String =
    java.util.Calendar.getInstance(l).getTime.toString

  val time: Endpoint[Time] =
    post("time" :: jsonBody[Locale]) { l: Locale =>
      Ok(Time(l, currentTime(new java.util.Locale(l.language, l.country))))
    }

  Await.ready(Http.server.serve(":8081", time.toService))
}
What People Say?
@mandubian on Twitter:

I think there is clearly room for great improvements using pure FP in Scala for HTTP API & #Finch is clearly a good candidate.

@tperrigo on Reddit:

I’m currently working on a project using Finch (with Circe to serialize my case classes to JSON without any boilerplate code – in fact, besides the import statements, I don’t have to do anything to transform my results to JSON) and am extremely impressed. There are still a few things in flux with Finch, but I’d recommend giving it a look.
