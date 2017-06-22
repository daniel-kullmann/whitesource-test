import WhiteSourcePlugin.autoImport._

lazy val dataCore = (crossProject in file("core"))
  .enablePlugins(WhiteSourcePlugin)
  .settings(
    name := "data.core",
    scalaVersion in ThisBuild := "2.11.8",
    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
    libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.8",
    libraryDependencies += "org.scalikejdbc" %% "scalikejdbc" % "2.5.0",
    libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.21"
  )

lazy val dataCoreJVM = dataCore.jvm

lazy val dataCoreJS = dataCore.js

lazy val dataSql = (crossProject in file("sql"))
  .enablePlugins(WhiteSourcePlugin)
  .settings(name := "data.sql")

lazy val dataSqlJVM = dataSql.jvm
  .dependsOn(dataCoreJVM)

lazy val dataSqlJS = dataSql.js
  .dependsOn(dataCoreJS)

lazy val root = project.in(file("."))
  .enablePlugins(WhiteSourcePlugin)
  .aggregate(dataCoreJS, dataCoreJVM, dataSqlJS, dataSqlJVM)
  .settings(
    exportJars := false,
    publishArtifact := false,
    publish := {},
    publishTo := None,
    publishLocal := {})
  .settings(
    credentials += Credentials(
      realm = "whitesource",
      host = "whitesourcesoftware.com",
      userName = "",
      passwd = "yadayada"
    ),
    whitesourceProduct in ThisBuild  := "yadayada",
    whitesourceAggregateProjectName in ThisBuild  := "yadayada"
  )

