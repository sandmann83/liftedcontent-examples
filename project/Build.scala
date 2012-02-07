import sbt._

import com.github.siasia._
import WebappPlugin.webappSettings
import Keys._
    
object WebBuild extends Build {
  val liftVersion = "2.4"

  lazy val container = Container("container")

  lazy val globalSettings = Seq(
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.10" % "test",
      "org.specs2" %% "specs2" % "1.7.1" % "test",
      "org.slf4j" % "slf4j-log4j12" % "1.6.4",
      "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default" withSources,
      "net.liftweb" %% "lift-util" % liftVersion % "compile->default" withSources,
      "net.liftweb" %% "lift-common" % liftVersion % "compile->default" withSources,
      "eu.sbradl" %% "liftedcontent-blog" % "1.0.0" % "compile"
    )
  )

  lazy val rootSettings = Seq(
    libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "8.1.0.v20120127" % "container"
  ) ++ container.deploy(
    "/cms" -> cmsExampleModule,
    "/blog" -> blogExampleModule
  )


  lazy val cmsExampleSettings = webappSettings ++ globalSettings
  lazy val blogExampleSettings = webappSettings ++ globalSettings

  lazy val cmsExampleModule = Project("CMSExample", file("CMSExample")) settings(cmsExampleSettings:_*)
  lazy val blogExampleModule = Project("BlogExample", file("BlogExample")) settings(blogExampleSettings:_*)

  lazy val root = Project("root", file(".")) settings(rootSettings:_*) aggregate(cmsExampleModule, blogExampleModule)
} 
