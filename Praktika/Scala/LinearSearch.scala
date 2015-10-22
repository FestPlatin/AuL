package versuch_2

/**
 * @author s71513
 */
object LinearSearch {
  
  def ls(a: Int, l: List[Int]): Boolean = l match {
    case Nil => false
    case h::t if h == a => true
    case h::t => ls(a, t)
  }
  
  
  def main(args: Array[String]) {
    println(ls(5, List(1,2,3)))
  }
}