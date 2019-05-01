import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"
val geotrellisVersion = "2.3.1"
lazy val root = (project in file("."))
  .settings(
    name := "SinusoidalToLatLngReprojection",
    resolvers +=    "LocationTech Releases" at "https://repo.locationtech.org/content/repositories/releases",
    libraryDependencies ++= Seq(
      "org.locationtech.spatial4j" % "spatial4j" % "0.7",
      "io.spray" %% "spray-json" % "1.3.4",
      "org.locationtech.geotrellis" %% s"geotrellis-vector" % geotrellisVersion,
      "org.locationtech.geotrellis" %% s"geotrellis-util" % geotrellisVersion,
      "org.locationtech.geotrellis" %% s"geotrellis-proj4" % geotrellisVersion,
      "org.noggit" % "noggit" % "0.8",
      scalaTest % Test)
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
