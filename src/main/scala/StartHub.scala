
/**
  * Created by maro on 14.05.2018.
  */
object StartHub {
  def main(args: Array[String]): Unit = {
    val carsManager = new CarsHBaseManager
    val hbaseManager = new SimpleHBaseManager("cars")
    val cars = carsManager.findCars("year", "2018")
    cars.map{m=> println(m.toString)}

  }
}