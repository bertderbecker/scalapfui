package io.github.bertderbecker.scalapfui.attribute

import io.github.bertderbecker.scalapfui.SimpleModifier
import io.github.bertderbecker.scalapfui.property.{NestedProperty, Property}

trait StoredAttribute[T] extends NestedProperty[T] {

  val initValue: Option[T]

  def crowd(prop: Property[T]): Unit = property.bindBidirectional(prop)

  def :=(newValue: T): SimpleModifier = SimpleModifier(() => update(newValue))

}
