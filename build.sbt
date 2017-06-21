import WhiteSourcePlugin.autoImport._

lazy val dataCore = (crossProject in file("core"))
  .enablePlugins(WhiteSourcePlugin)
  .settings(
    name := "data.core",
    scalaVersion in ThisBuild := "2.11.8",
    libraryDependencies += "com.lihaoyi" %% "fastparse" % "0.4.1",
    libraryDependencies += "com.sksamuel.scapegoat" %% "scalac-scapegoat-plugin" % "1.3.0",
    libraryDependencies += "com.typesafe" % "config" % "1.3.0",
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0",
    libraryDependencies += "com.zaxxer" % "HikariCP" % "2.5.1",
    libraryDependencies += "io.github.cquiroz" %% "scala-java-time" % "2.0.0-M7",
    libraryDependencies += "io.suzaku" %% "boopickle" % "1.2.6",
    libraryDependencies += "org.json4s" %% "json4s-native" % "3.3.0",
    libraryDependencies += "org.scala-js" %% "scalajs-library" % "0.6.14",
    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
    libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.8",
    libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.8",
    libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.2",
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

