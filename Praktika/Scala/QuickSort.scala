package versuch_2

/**
 * @author s71513
 */
object QuickSort {
  
  def qs(l: List[Int]): List[Int] = l match {
    case Nil => Nil
    case h::t =>
      val (li, re) = t.partition { x => (x <= h) }
      qs(li) ::: h ::qs(re)
  }
  
  
  
  def main(args: Array[String]) {
    
    println(qs(List(5,4,2,3)))
    
    val l = (for(i <- 0 until 5) yield i).toList
    println(qs(l))
  }
  
}