name := """ppt-to-video"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  // https://mvnrepository.com/artifact/org.webjars/jquery
  "org.webjars" % "jquery" % "2.1.4",
  // https://mvnrepository.com/artifact/org.webjars/swfobject
  "org.webjars" % "swfobject" % "2.2",
// Test
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

