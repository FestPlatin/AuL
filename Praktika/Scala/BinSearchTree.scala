package versuch_2

/**
 * @author s71513
 */
object BinSearchTree {
  abstract sealed class Btree
  case class Empty() extends Btree
  case class Node(value: Int, l: Btree, r: Btree) extends Btree
  
  def find(t:Btree, v:Int): Boolean = t match {
    case Empty() => false
    case Node(value, left, right) =>
      if(value == v)
        true;
      else if (v > value) 
        find(right, v)
      else find(left, v)
  }

  def add(t: Btree, v: Int): Btree = t match {
    case Empty() => new Node(v, Empty(), Empty())
    case Node(value, left, right) =>
      if(v == value)
        t
      else if (v > value)
        Node(value, left, add(right,v))
      else Node(value, add(left,v), right)
  }

  def remove(t: Btree, v: Int): Btree = {
    def findMin(t: Btree): Int = t match {
      case Empty() =>
         throw new IllegalArgumentException("Fehler")
      case Node(value, Empty(), _) =>
        value
      case Node(_, left, _) =>
        findMin(left)
    }
    
    t match {
      case Empty() => new Empty()
      case Node(value, Empty(), Empty()) =>
        if (value == v)
          Empty()
        else 
          t
      case Node(value, left, right) =>
        if (value == v)
        {
          val min = findMin(right)
          Node(min, left, remove(right, min))
        }
        else if(v > value)
          Node(value, left, remove(right, v))
        else
          Node(value, remove(left, v), right)
    }
  }
  
  def main(args: Array[String]) {
    val t = Node(3, Node(1, Empty(), Empty()), Node(5, Empty(), Empty()))
    println(t)
    println(find(t, 5))
    println(add(t,9))
    println(remove(t, 5))
  }
}