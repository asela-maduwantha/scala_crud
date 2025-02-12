ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Book"
  )


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"           % "10.2.10",
  "com.typesafe.akka" %% "akka-stream"         % "2.6.19",
  "com.typesafe.slick" %% "slick"              % "3.3.3",
  "com.typesafe.slick" %% "slick-hikaricp"     % "3.3.3",
  "org.postgresql"     %  "postgresql"         % "42.5.0",
  "ch.qos.logback"     %  "logback-classic"    % "1.2.11",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.10",
  "io.spray"          %% "spray-json"          % "1.3.6"
)
