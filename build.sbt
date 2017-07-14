import WhiteSourcePlugin.autoImport._

lazy val test = (crossProject in file("test"))
  .enablePlugins(WhiteSourcePlugin)
  .settings(
    logLevel in Global := Level.Debug,
    name := "test",
    scalaVersion in ThisBuild := "2.11.8",
    libraryDependencies += "org.json4s" %% "json4s-native" % "3.3.0"
  )

lazy val testJVM = test.jvm

lazy val testJS = test.js

lazy val root = project.in(file("."))
  .enablePlugins(WhiteSourcePlugin)
  .aggregate(testJS, testJVM)
  .settings(
    credentials += Credentials(
      realm = "whitesource",
      host = "whitesourcesoftware.com",
      userName = "",
      passwd = sys.env.get("WHITESOURCE_API_KEY").get
    ),
    whitesourceProduct in ThisBuild  := sys.env.get("WHITESOURCE_PRODUCT").get,
    whitesourceAggregateProjectName in ThisBuild  := sys.env.get("WHITESOURCE_PROJECT_NAME").get,
    whitesourceAggregateProjectToken in ThisBuild  := sys.env.get("WHITESOURCE_PROJECT_TOKEN").get
  )
