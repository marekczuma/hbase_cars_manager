import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog

/**
  * Created by maro on 06.06.2018.
  */
class CarsSHCManager(val spark: SparkSession) {
  val df = spark
    .read
    .option(HBaseTableCatalog.tableCatalog, catalog)
    .format("org.apache.spark.sql.execution.datasources.hbase")
    .load()

  def showCars(): Unit ={
    df.show(false)
  }

  def catalog =
    s"""{
       |"table":{"namespace":"default", "name":"cars"},
       |"rowkey":"key",
       |"columns":{
       |"col0":{"cf":"rowkey", "col":"key", "type":"string"},
       |"col1":{"cf":"information", "col":"brand", "type":"string"},
       |"col2":{"cf":"information", "col":"model", "type":"string"},
       |"col3":{"cf":"information", "col":"year", "type":"string"},
       |"col4":{"cf":"information", "col":"min_otomoto_price", "type":"string"},
       |"col5":{"cf":"information", "col":"max_otomoto_price", "type":"string"}
       |}
       |}""".stripMargin
}
