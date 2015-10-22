package versuch_2

/**
 * @author s71513
 */
object Fac {

  def fac1(n: Int): Int = {
    if(n == 0) 1
    else n * fac1(n-1)
  }
  
  // Pattern Matching
  def fac2(n: Int): Int = n match {
    case 0 => 1
    // default -Zweig
    case _ => n * fac1(n-1)
  }
  
  def main(args: Array[String]) {
    println(fac2(5))
  }
}