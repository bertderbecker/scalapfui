package io.github.bertderbecker.scalapfui.attribute

import io.github.bertderbecker.scalapfui.RunLater
import io.github.bertderbecker.scalapfui.property.{NestedProperty, Property}

trait StoredAttribute[T] extends NestedProperty[T] with StoredReadableAttribute[T] {

  val initValue: Option[T]

  def crowd(prop: Property[T]): Unit = property.bindBidirectional(prop)

  def :=(newValue: T): RunLater = RunLater(() => update(newValue))

}
