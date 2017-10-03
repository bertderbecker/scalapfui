package io.github.bertderbecker.scalapfui.property

import scala.language.higherKinds

trait NestedReadableProperty[T, CC[X] <: ReadableProperty[X]] extends ReadableProperty[T] {

  override def calcValue: Option[T] = property.calcValue

  override def processOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = property.processOnChange(op)

  override def removeOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = property.removeOnChange(op)

  val property: CC[T]

}