
name := """play-slick"""
organization := "play-slick"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

resolvers += Resolver.jcenterRepo // silhouette

lazy val defaultDependencies = {
  val scalikeJdbcDependencies = {
    val scalikeJdbcVersion = "3.2.3"
    List(
      "org.scalikejdbc" %% "scalikejdbc"        % scalikeJdbcVersion,
      "org.scalikejdbc" %% "scalikejdbc-config" % scalikeJdbcVersion,
      "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.6.0-scalikejdbc-3.2"
    )
  }

  List(
    evolutions, jdbc, guice,
    "com.h2database"  %  "h2"                 % "1.4.197",
    "ch.qos.logback"  %  "logback-classic"    % "1.2.+",
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
  ) ++ scalikeJdbcDependencies
}
libraryDependencies ++= Seq(
  "com.mohiva" %% "play-silhouette" % "5.0.7",
  "com.mohiva" %% "play-silhouette-password-bcrypt" % "5.0.7",
  "com.mohiva" %% "play-silhouette-crypto-jca" % "5.0.7",
  "com.mohiva" %% "play-silhouette-persistence" % "5.0.7",
  "com.mohiva" %% "play-silhouette-testkit" % "5.0.7" % "test",
)

libraryDependencies ++= defaultDependencies

// sbt scalafmtでコードフォーマット
//scalafmtConfig := Some(file(".scalafmt.conf"))
//scalafmtOnCompile := true // compile時に自動でコードフォーマット

evictionWarningOptions in update := EvictionWarningOptions.default.withWarnEvictionSummary(false)

libraryDependencies += "javax.xml.bind" % "jaxb-api" % "2.2.12"