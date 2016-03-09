organization  := "scalegray"

version       := "0.1"

scalaVersion  := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")


lazy val circeVersion = "0.3.0"


resolvers ++= Seq(
Resolver.bintrayRepo("scalaz", "releases")
)


libraryDependencies ++= {
  val akkaV = "2.4.0"
  val akkaStreamVersion = "2.0-M1"
  val sprayV = "1.3.3"
  val scalazVersion = "7.2.0"


  Seq(
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test",
    "com.github.finagle" %% "finch-core" % "0.10.0",
    "com.github.finagle" %% "finch-circe" % "0.10.0",
    "com.twitter" %% "twitter-server" % "1.18.0",
    "org.apache.thrift" % "libthrift" % "0.9.3",
    "io.circe" %% "circe-core" % circeVersion,
  //  "io.circe" %% "circe-jawn" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    "org.scalaz" % "scalaz-core_2.11" % "7.2.0",
    "io.jvm.uuid" %% "scala-uuid" % "0.2.1"
  //  "org.scalaz" %% "scalaz-core" % scalazVersion
    //"org.scalaz" %% "scalaz-iteratee_2.11" % scalazVersion,
    //"org.scalaz" %% "scalaz-effect_2.11" % scalazVersion,
    //"org.scalaz" %% "scalaz-concurrent_2.11" % scalazVersion % "test"
  //  "io.circe" %% "circe-jackson" % circeVersion
  )
}

Revolver.settings
