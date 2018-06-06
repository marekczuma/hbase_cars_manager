name := "hbase-project"

version := "1.0"

scalaVersion := "2.11.4"

resolvers += "Hortonworks Repository" at "http://repo.hortonworks.com/content/repositories/releases/"

libraryDependencies ++= {
  Seq(
    "org.apache.hbase" % "hbase-common" % "1.2.6",
    "org.apache.hbase" % "hbase-client" % "1.2.6",
    "org.apache.hadoop" % "hadoop-common" % "3.0.0",
    "org.apache.spark" % "spark-core_2.11" % "2.1.1",
    "org.apache.spark" % "spark-sql_2.11" % "2.1.1",
    "com.hortonworks" % "shc-core" % "1.1.1-2.1-s_2.11",
    "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.7.4"
  )
}