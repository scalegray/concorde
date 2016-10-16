package com.scalegray

import io.jvm.uuid._
import scalaz._
import Scalaz._
import scalaz.Validation
import scalaz.Validation.FlatMap._

/*
 * A seperate UID class just to extend the UID later to integrate twitter snowflake for other usecases.
 */
object UID {


  def getUID: ValidationNel[Throwable, Long] = {
     (Validation.fromTryCatchThrowable[Long, Throwable] {
       io.jvm.uuid.UUID.random.leastSigBits.abs
     } leftMap { t: Throwable =>
        new Throwable("""UID failed to generate""")
     }).toValidationNel.flatMap { id: Long => Validation.success[Throwable, Long](id).toValidationNel}
  }
}
