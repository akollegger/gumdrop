import sbt._
import Keys._
import sbtassembly.Plugin._
import AssemblyKeys._

object Dependencies {
    val httpClient = "org.apache.httpcomponents" % "httpclient" % "4.2.3"
    val fluentHttpClient = "org.apache.httpcomponents" % "fluent-hc" % "4.2.3"
    val jopt =  "net.sf.jopt-simple" % "jopt-simple" % "4.4"
    val jgoodies =  "com.jgoodies" % "forms" % "1.2.1"
}

object GumdropBuild extends Build {
    
    import Dependencies._

    lazy val gumdrop = Project(id = "gumdrop",
                            base = file("."),
                            settings = Defaults.defaultSettings ++ assemblySettings
                            ) settings (
                                mainClass in assembly := Some("org.akollegger.gumdrop.GumdropTool"),
                                jarName in assembly := "gumdrop.jar",
                                autoScalaLibrary := false,
                                libraryDependencies ++= Seq(httpClient, fluentHttpClient, jopt, jgoodies)
                            )

}

