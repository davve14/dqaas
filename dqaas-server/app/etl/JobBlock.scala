package etl

class JobBlock(
    id: Int,
    name: String,
    description: String,
    fromTable: String,
    toTable: String,
    fromColumns: List[String],
    toColumns: List[String]
    ) {
  
  def printId() = println(id)
}





