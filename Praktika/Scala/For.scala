package versuch_2

/**
 * @author s71513
 */
object For {
  
  // häßlich
  def printArray1(a: Array[String]) {
    for(i <- 0 until a.length)
      println(a(i))
  }
  
  // besser
  def printArray2(a: Array[String]) {
    for(i <- a)
      println(i)
  }
  
  def printList(l: List[String]) {
    for(i <- l)
      println(i)
  }
  
  
  def main(args: Array[String]) {
    println("Möglichkeit 1:")
    printArray2(Array("This", "is", "an", "array"))
    
    println("Möglichkeit 2:")
    printList(List("This", "is", "an", "list"))
    
    println("Weitere Möglichkeit:")
    List("This", "is", "an", "list").foreach { println }
  }
}