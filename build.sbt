import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "fscoward",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Undine",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.google.guava" % "guava" % "25.0-jre"
    )
  )
