import org.apache.spark.sql
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.datasources.hbase.HBaseTableCatalog
import org.apache.spark.sql.types.DataTypes
import org.apache.spark.sql.types._

/**
  * Created by maro on 14.05.2018.
  */
case class Record(col0: Int, col1: Int, col2: Boolean)
object StartHub {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder()
      .appName("Spark HBase Example")
      .master("local")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")

    val shc = new CarsSHCManager(spark)
    shc.showCars()

//    println("")
//    println(" ====== === WYNIK COUNT === =====")
//    println("")
//
//    def catalog =
//      s"""{
//         |"table":{"namespace":"default", "name":"table1"},
//         |"rowkey":"key",
//         |"columns":{
//         |"col0":{"cf":"rowkey", "col":"key", "type":"int"},
//         |"col1":{"cf":"cf1", "col":"col1", "type":"int"},
//         |"col2":{"cf":"cf1", "col":"col2", "type":"boolean"}
//         |}
//         |}""".stripMargin
//    val artificialData = (0 to 100).map(number => Record(number, number, number % 2 == 0))
//    println("")
//    println(" ====== === CATALOG === =====")
//    println(catalog)
//    println("")
//    println(" ====== === ARTIFICIAL DATA === =====")
//    println(artificialData.toString())
//    println("")
//    println(" ====== === SPARK TO DATA FRAME === =====")
//    println("")
//    import spark.implicits._
//
//    val trainingData = Seq(("greeting", "how are you?"), ("greeting", "how is your day?"), ("greeting", "good day"), ("greeting","how is it going today?"),
//      ("goodbye", "have a nice day"), ("goodbye", "see you later"), ("goodbye", "have a nice day"), ("goodbye", "talk to you soon"),
//      ("sandwich", "make me a sandwich"), ("sandwich", "can you make a sandwich?"), ("sandwich", "having a sandwich today?"), ("sandwich", "what's for lunch?"))
//    val trainingFrame = trainingData.toDF("class", "sentence")
//    println("")
//    println(" ====== === SPARK CREATE DATA FRAME  === =====")
//    println("")
//    spark
//      .createDataFrame(artificialData)
//      .write
//      .option(HBaseTableCatalog.tableCatalog, catalog)
//      .option(HBaseTableCatalog.newTable, "5")
//      .format("org.apache.spark.sql.execution.datasources.hbase")
//      .save()
//    println("")
//    println(" ====== === END SPARK CREATE DATA FRAME  === =====")
//    println("")
////
//    // read
//    val df = spark
//      .read
//      .option(HBaseTableCatalog.tableCatalog, catalog)
//      .format("org.apache.spark.sql.execution.datasources.hbase")
//      .load()
//
//    println("")
//    println(" ====== === SPARK LOAD DATA FRAME  === =====")
//    println("")
//    println(df.count())
//    df.show(false)
//    println("")
//    println(" ====== === END SPARK LOAD DATA FRAME  === =====")
//    println("")
//    Jak znaleźćwszystkie tesle:
//    val carsManager = new CarsHBaseManager
//    val hbaseManager = new SimpleHBaseManager("cars")
//    val cars = carsManager.findCars("brand", "tesla")
//    cars.map{m=> println(m.toString)}

  }
}