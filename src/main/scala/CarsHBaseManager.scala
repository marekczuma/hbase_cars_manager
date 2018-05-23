import org.apache.hadoop.hbase.util.Bytes

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.Random

/**
  * Created by maro on 14.05.2018.
  */
class CarsHBaseManager(val safetyCounter :Int = 100) {
  val hBaseMAnager = new SimpleHBaseManager("cars")

  def addCarsFromPreparedSet(): Unit ={
    val data = prepareData()
    data.foreach(d => addCar(d("brand"), d("model"), d("year")))
  }

  def prepareData(): Array[Map[String, String]] ={
    Array(Map("brand" -> "Mazda", "model" -> "3", "year" -> "2018"),
      Map("brand" -> "Tesla", "model" -> "Roadster", "year" -> "2008"),
      Map("brand" -> "Tesla", "model" -> "S", "year" -> "2012"),
      Map("brand" -> "Audi", "model" -> "A4", "year" -> "2001"))
  }

  def findCars(attribute :String, value :String): ListBuffer[Car] = {
    val results = hBaseMAnager.findRows("information", attribute, value)
    val iter = results.iterator()
    var tmpCounter = 0
    var carsCollection = new ListBuffer[Car]();
    while(iter.hasNext() && tmpCounter < safetyCounter){
      tmpCounter += 1;
      val nextRow = iter.next();
      val rowkey = Bytes.toString(nextRow.getRow)
      val brand = Bytes.toString(nextRow.getValue(Bytes.toBytes("information"), Bytes.toBytes("brand")))
      val model = Bytes.toString(nextRow.getValue(Bytes.toBytes("information"), Bytes.toBytes("model")))
      val year = Bytes.toString(nextRow.getValue(Bytes.toBytes("information"), Bytes.toBytes("year")))
      val maxOtoMotoPrice = Bytes.toString(nextRow.getValue(Bytes.toBytes("information"), Bytes.toBytes("max_otomoto_price"))).toFloat
      val minOtoMotoPrice = Bytes.toString(nextRow.getValue(Bytes.toBytes("information"), Bytes.toBytes("min_otomoto_price"))).toFloat
      val car :Car = new Car(rowkey, brand, model, year, maxOtoMotoPrice, minOtoMotoPrice)
      carsCollection += car
    }
    carsCollection
  }

  def addCar(brand :String, model :String, year :String): Unit ={
    val rowkey = generateRowKey(brand, model)
    val data = Map("brand" -> brand, "model" -> model, "year" -> year)
    hBaseMAnager.putRowToTable(rowkey, "information", data)
  }

  def addCar(data :Map[String, String]): Unit = {
    val brand = data("brand")
    val model = data("model")
    val rowkey = generateRowKey(brand, model)
    hBaseMAnager.putRowToTable(rowkey, "information", data)
  }

  def loadFromCSV(): Unit ={
    val bufferedSource = Source.fromFile("/Users/maro/apps/hbase-project/src/main/scala/cars.csv")
    val qualifiers_line = bufferedSource.getLines().take(1).next()
    val qualifiers = qualifiers_line.split(",").map(_.trim)
    for (line <- bufferedSource.getLines.drop(1)) {
      val car = line.split(",").map(_.trim)
      val data_for_hbase = (qualifiers zip car).toMap
      addCar(data_for_hbase)
    }
    bufferedSource.close
  }

  private def generateRowKey(brand :String, model :String): String ={
    brand + "_" + model + "_" + Random.nextInt(100000)
  }

}
