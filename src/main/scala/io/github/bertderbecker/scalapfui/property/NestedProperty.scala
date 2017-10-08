package io.github.bertderbecker.scalapfui.property

import scala.language.higherKinds

trait NestedProperty[T] extends Property[T] {

  val writableProperty: WritableProperty[T]

  val readableProperty: ReadableProperty[T]


  override def doBinding(other: ReadableProperty[T]): Unit = writableProperty.bindTo(other)

  override def calcValue: Option[T] = readableProperty.value

  override def processOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = readableProperty.onChangeFull(op)

  override def removeOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = readableProperty.removeOnChange(op)

  override def doUpdate(newValue: T): Unit = writableProperty.doUpdate(newValue)

}
