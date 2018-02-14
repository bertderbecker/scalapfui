package io.github.bertderbecker.scalapfui.property

import scala.language.higherKinds

trait NestedProperty[T] extends Property[T] {

  val property: Property[T]

  override def bindBidirectional(other: Property[T]): Unit = property.bindBidirectional(other)

  override def doBidirectionalBinding(other: Property[T]): Unit = property.doBidirectionalBinding(other)

  override def doBinding[B <: T](other: ReadableProperty[B]): Unit = property.bindTo(other)

  override def calcValue: Option[T] = property.value

  override def processOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = property.onChangeFull(op)

  override def removeOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = property.removeOnChange(op)

  override def doUpdate(newValue: T): Unit = property.doUpdate(newValue)

}
