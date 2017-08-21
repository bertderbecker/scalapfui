package io.github.bertderbecker.scalapfui.attribute

import io.github.bertderbecker.scalapfui.Math.Calculatable
import io.github.bertderbecker.scalapfui.property.ReadableProperty

trait ReadableAttribute[T, Native]
  extends Calculatable[T, ReadableAttribute[?, Native]] {

  def readablePropertyExtr: Native => ReadableProperty[T]

  override val self: ReadableAttribute[T, Native] = this

}