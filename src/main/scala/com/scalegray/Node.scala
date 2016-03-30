package com.scalegray


import scalaz._
import Scalaz._
import scalaz.Validation
import scalaz.Validation.FlatMap._
import io.jvm.uuid._



case class Node(uid: Long) {
//  def getUID(): Long = {
//    return 999685664646464L
//  }
}

object Node {

 def checkId(id: Long): ValidationNel[Throwable, Option[Node]] = {
   //do a call to db class and fetch an Option[Node]
      return Validation.success[Throwable, Option[Node]](some(new Node(new UID().getUID
      match {case Success(s) => s}))
    ).toValidationNel
 }
}
