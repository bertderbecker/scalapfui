package io.github.bertderbecker.scalapfui.attribute

import io.github.bertderbecker.scalapfui.property.{Property, ReadableProperty, WritableProperty}

trait Attribute[T, Native]
  extends ReadableAttribute[T, Native]
    with WritableAttribute[T, Native] {

  def propertyExtr: Native => Property[T]

  override def readablePropertyExtr: Native => ReadableProperty[T] =
    propertyExtr

  override def writablePropertyExtractor: Native => WritableProperty[T] =
    propertyExtr
}