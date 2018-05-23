name := "hbase-project"

version := "1.0"

scalaVersion := "2.11.4"

libraryDependencies ++= {
  Seq(
    "org.apache.hbase" % "hbase-common" % "1.2.6",
    "org.apache.hbase" % "hbase-client" % "1.2.6",
    "org.apache.hadoop" % "hadoop-common" % "3.0.0"
  )
}