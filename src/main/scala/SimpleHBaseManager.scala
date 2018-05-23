/**
  * Created by maro on 14.05.2018.
  */
import org.apache.hadoop.hbase._
import org.apache.hadoop.hbase.client.{ConnectionFactory, Put, ResultScanner, Scan}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter

class SimpleHBaseManager(tableName :String) {
  val c = HBaseConfiguration.create()
  val connection = ConnectionFactory.createConnection(c)
  val table = connection.getTable(TableName.valueOf(tableName))


  def putRowToTable(rowkey :String, columnFamily :String, data :Map[String, String]): Unit ={
    val p = new Put(Bytes.toBytes(rowkey))
    data.foreach{
      case(column, value) => {
        p.addImmutable(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value))
      }
    }
    table.put(p)
  }

  def findRows(columnFamily :String, qualifier :String, value :String) :ResultScanner ={
    val scanner = new Scan()
    val filter = new SingleColumnValueFilter(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier),CompareOp.EQUAL,
      Bytes.toBytes(value))
    scanner.setFilter(filter)
    table.getScanner(scanner)
  }

  def closeTable(): Unit ={
    table.close()
  }
}
