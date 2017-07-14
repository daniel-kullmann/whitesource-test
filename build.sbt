import WhiteSourcePlugin.autoImport._

lazy val core = (crossProject in file("core"))
  .enablePlugins(WhiteSourcePlugin)
  .settings(
    name := "core",
    scalaVersion in ThisBuild := "2.11.8",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.8",
    libraryDependencies += "org.json4s" %% "json4s-native" % "3.3.0"
  )

lazy val coreJVM = core.jvm

lazy val coreJS = core.js

lazy val root = project.in(file("."))
  .enablePlugins(WhiteSourcePlugin)
  .aggregate(coreJS, coreJVM)
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
