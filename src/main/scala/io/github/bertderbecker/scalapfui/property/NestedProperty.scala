package io.github.bertderbecker.scalapfui.property

import scala.language.higherKinds

trait NestedProperty[T, CC[_]] extends Property[T] {

  val nestedWritableProperty: NestedWritableProperty[T]

  val nestedReadableProperty: NestedReadableProperty[T, CC]


  override def doBinding(other: ReadableProperty[T]): Unit = nestedWritableProperty.bindTo(other)

  override def calcValue: Option[T] = nestedReadableProperty.value

  override def processOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = nestedReadableProperty.onChangeFull(op)

  override def removeOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit = nestedReadableProperty.removeOnChange(op)

  override def doUpdate(newValue: T): Unit = nestedWritableProperty.doUpdate(newValue)

}
