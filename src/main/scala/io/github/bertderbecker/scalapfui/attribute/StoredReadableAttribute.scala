package io.github.bertderbecker.scalapfui.attribute

import io.github.bertderbecker.scalapfui.property.{NestedReadableProperty, Property, ReadableProperty}

trait StoredReadableAttribute[T] extends NestedReadableProperty[T, Property] {

  def crowd(prop: ReadableProperty[T]): Unit = property.bindTo(prop)

}