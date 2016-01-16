package versuch_2

import scala.util.Random

case class HMM(startp: Array[Double], transp: Array[Array[Double]], emitp: Array[Array[Double]]) {
  val states = startp.length
  val chars = emitp(0).length
  
  def sampleSeq(n: Int) = {
    def intervals(p: Array[Double]) = p.scan(0.0)(_ + _).tail
    
    val startpl = intervals(startp)
    val transpl = transp.map(intervals)
    val emitpl = emitp.map(intervals)
    
    def sample(interv: Array[Double]) = {
      val x = Random.nextDouble
      interv.indexWhere(x <= _) match {
        case -1 => throw new IllegalArgumentException("Wahrscheinlichkeiten falsch")
        case s => s
      }
    }
    
    val stateSeq = Array.ofDim[Int](n)
    stateSeq(0) = sample(startpl)
    for(i <- 1 until stateSeq.length)
      stateSeq(i) = sample(transpl(stateSeq(i-1)))
    val outputSeq = stateSeq.map(s => sample(emitpl(s)))
    (outputSeq, stateSeq)
  }
  
  def viterbi(output: Array[Int]) = {
    val t = Array.ofDim[(Double, Int)](states, output.length)
    for(k <- 0 until output.length)
      for(z <- 0 until states) {
        t(z)(k) = 
          if(k == 0) (emitp(z)(output(0)) * startp(z), -1)
          else {
            val l = (for(zz <- 0 until states) yield transp(zz)(z) * t(zz)(k-1)._1)
              (emitp(z)(output(k)) * l.max, l.indexOf(l.max))
          }
      }
    val stateSeq = Array.ofDim[Int](output.length)
    val q = for(z <- 0 until states) yield t(z)(output.length-1)._1
    stateSeq(output.length - 1) = q.indexOf(q.max)
    for(k <- output.length - 2 to 0 by -1) {
      stateSeq(k) = t(stateSeq(k+1))(k+1)._2
    } 
    stateSeq
    }
  
  def forward(output: Array[Int]) = {
    val alpha = Array.ofDim[Double](output.length, states)
    for (j <- (0 until states))
      alpha(0)(j) = emitp(j)(output(0)) * startp(j)
    for (t <- 1 until output.length; j <- 0 until states)
      alpha(t)(j) = emitp(j)(output(t)) * (for(l <- (0 until states)) yield transp(l)(j) * alpha(l-1)(j-1)).sum 
  }
}