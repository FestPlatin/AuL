package versuch_2

/**
 * @author s71513
 */
object Heap {
  abstract sealed class Btree {
    val s: Int;
  }
  
  case class Empty() extends Btree {
    val s = 0;
  }
  
  case class Node(value: Int, l: Btree, r: Btree) extends Btree {
    val s = Math.min(l.s, r.s) + 1 
  }
  
  def merge(l : Btree, r: Btree): Btree = {
    // Prüfe Linksbaumeigenschaft und tausche ggf.
    def m(root: Int, l: Btree, r: Btree) = {
      if(l.s >= r.s)
        Node(root, l, r)
      else
        Node(root, r, l)
    }
        
    (l, r) match {
      // Ende der Rekursion, linker Teilbaum leer
      case (Empty(), _) => r
      
      // Ende der Rekursion, rechter Teilbaum leer
      case (_, Empty()) => l
      
      case (Node(left_value, left_lBtree, left_rBtree), Node(right_value, right_lBtree, right_rBtree)) =>

        // Wir betrachten den Baum mit dem kleinsten Wurzelwert...
        if(left_value < right_value) {
          // und betrachten dessen rechten Teilbaum. Merge wird rekursiv aufgerufen 
          val result = merge(left_rBtree, r)
          
          // Nach der Rückkehr wird die Linksbaum Eigenschaft geprüft und die beiden Bäume gemergt
          m(left_value, result, left_lBtree)
        }
        else {
          val result = merge(right_lBtree, l)
          m(right_value, result, right_lBtree)
        }
    }
  }
  
  def put(heap : Btree, value : Int): Btree = {
    merge(heap, Node(value, Empty(), Empty()))
  }
  
  def make(l: List[Int]):Btree = {
    l.foldLeft(Empty(): Btree)(put)
  }
   
  
  def main(args: Array[String]) {
    val h = make(List(5,3,7))
    println(h);
    println(put(h,8));
  }
}