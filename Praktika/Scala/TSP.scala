package versuch_2

object TSP {

  case class TSP(dist: Array[Array[Double]]) {
    val n = dist.length
    val n1 = n - 1
    val m = 1 << n1
    val l = Array.fill(n,m)(-1.0)
    
    def shortestCycle = {
      def len(s: List[Int], i: Int) : Double = s match {
        case Nil => dist(i)(n1)
        case _ =>
          val sIdx = s.map( 1 << _ ).sum
          if(l(i)(sIdx) == -1.0)
            l(i)(sIdx) = (for(j <- s) yield dist(i)(j) + len(s.filter(_ != j), j)).min
            l(i)(sIdx)
      }
      val a = (for(i <- (0 to n1 - 1)) yield i).toList
      len(a, n1)
    }
  }
  
  def main(args: Array[String]) {
    val inf = Double.PositiveInfinity
    val dist = Array(Array(0.0, 4.0, 1.0, 6.0, inf, inf),
        Array(4.0, 0.0, 1.0,inf, 6.0,inf),
        Array(1.0, 1.0, 0.0,inf, 8.0, 2.0),
        Array(6.0, inf, inf, 0.0, inf, 2.0),
        Array(inf, 5.0, 8.0, inf, 0.0, 5.0),
        Array(inf, inf, 2.0, 2.0, 5.0, 0.0))
    println(TSP(dist).shortestCycle)
  }
 
}