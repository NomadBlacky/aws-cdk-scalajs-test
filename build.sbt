ThisBuild / scalaVersion := "2.13.3"
ThisBuild / organization := "dev.nomadblacky"
ThisBuild / organizationName := "NomadBlacky"
ThisBuild / name := "aws-cdk-fargate"
ThisBuild / version := "0.1.0-SNAPSHOT"

val versions = new {
  val awscdk = "1.64.1"
}

lazy val commonSettings = Seq(
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest-funsuite" % "3.2.2" % Test
  )
)

lazy val server = (project in file("server"))
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "cask" % "0.7.5"
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
