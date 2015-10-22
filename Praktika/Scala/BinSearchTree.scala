package versuch_2

/**
 * @author s71513
 */
object BinSearchTree {
  abstract sealed class Btree
  case class Empty() extends Btree
  case class Node(value: Int, l: Btree, r: Btree) extends Btree
  
//  def find(t:Btree, v:Int): Boolean = t match {
//    true
//  }
//  
//  def add(t: Btree, v: Btree): Btree = {}
//
//  def remove(t: Btree, v: Int): Btree = {}
  
  def main(args: Array[String]) {
    val t = Node(3, Node(1, Empty(), Empty()), Node(5, Empty(), Empty()))
    println(t)
  }
}