name := """play-slick"""
organization := "play-slick"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += "com.h2database" % "h2" % "1.4.196"
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  "com.typesafe.play" %% "play-slick" % "4.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "4.0.0",
  "com.h2database" % "h2" % "1.4.196",
  guice,
  ws,
)

libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.2.12"