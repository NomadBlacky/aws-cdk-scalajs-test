ThisBuild / scalaVersion := "2.13.2"
ThisBuild / organization := "dev.nomadblacky"
ThisBuild / organizationName := "NomadBlacky"
ThisBuild / name := "aws-cdk-fargate"
ThisBuild / version := "0.1.0-SNAPSHOT"

val versions = new {
  val awscdk = "1.46.0"
}

lazy val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest-funsuite" % "3.2.0" % Test
  )
)

lazy val server = (project in file("server"))
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "cask" % "0.6.7"
    ),
    dockerBaseImage := "adoptopenjdk:11-hotspot",
    dockerExposedPorts := Seq(8080)
  )

lazy val cdk = (project in file("cdk"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "software.amazon.awscdk" % "ecs-patterns" % versions.awscdk
    )
  )
