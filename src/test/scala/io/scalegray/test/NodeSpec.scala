package com.scalegray.test


import org.scalatest._
import com.scalegray._
import io.jvm.uuid._


class NodeSpec extends FlatSpec {

  "Node" should "return a UID" in {
    val uid: Long = 9223372036854775807L
    val stack = new Node(uid)
    stack.getUID()
    //assert(1 === 1)
  }
}
