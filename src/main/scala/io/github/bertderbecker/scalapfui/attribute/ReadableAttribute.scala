package io.github.bertderbecker.scalapfui.attribute

import io.github.bertderbecker.scalapfui.Math.Calculatable
import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.property.{NestedReadableProperty, Property, ReadableProperty}

trait ReadableAttribute[T, Native]
  extends Calculatable[T, ReadableAttribute[?, Native]] {

  def readablePropertyExtr: Native => ReadableProperty[T]

  def ==>(other: StoredReadableAttribute[T]) = Modifier.apply { (native: Native) =>
    other.crowd(readablePropertyExtr(native))
  }


  override val self: ReadableAttribute[T, Native] = this

}