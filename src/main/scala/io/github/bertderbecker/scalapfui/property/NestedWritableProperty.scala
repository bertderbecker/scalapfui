package io.github.bertderbecker.scalapfui.property

import scala.language.higherKinds

trait NestedWritableProperty[T, CC[X] <: WritableProperty[X]] extends WritableProperty[T] {

  val property: CC[T]

  override def doUpdate(newValue: T): Unit = property.doUpdate(newValue)

}