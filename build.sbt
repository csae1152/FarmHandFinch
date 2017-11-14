name := """finch-seed"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

// what class starts the server
mainClass in (Compile, run) := Some("FarmHandApplication")

// global package settings
packageDescription := "Custom application configuration"

libraryDependencies ++= Seq(
	  "com.github.finagle" %% "finch-core" % "0.10.0"
)

resolvers ++= Seq(
	 Resolver.sonatypeRepo("snapshots")
)

enablePlugins(JavaAppPackaging)
