package spark

class RunProfile {
  def RunProfile(targetTableName: String, dbName: String, targetSchema: List[String]): String = {
    println("Run profile in Spark")
    println("Logging on column level..")
    return "0.78" //table level dq value
  }
}