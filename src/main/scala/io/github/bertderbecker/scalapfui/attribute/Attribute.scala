package io.github.bertderbecker.scalapfui.attribute

import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.extras.Includes._
import io.github.bertderbecker.scalapfui.property.{Property, ReadableProperty, WritableProperty}

trait Attribute[T, Native]
  extends ReadableAttribute[T, Native]
    with WritableAttribute[T, Native] {

  def propertyExtr: Native => Property[T]

  override def readablePropertyExtr: Native => ReadableProperty[T] =
    propertyExtr

  override def writablePropertyExtractor: Native => WritableProperty[T] =
    propertyExtr


  def ==>(other: StoredAttribute[T]) = Modifier.apply { (native: Native) =>
    other.initValue.ifDefined(propertyExtr(native).update)
    other.crowd(propertyExtr(native))
  }

}