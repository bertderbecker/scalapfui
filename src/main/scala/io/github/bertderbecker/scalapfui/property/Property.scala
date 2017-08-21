package io.github.bertderbecker.scalapfui.property

trait Property[T] extends ReadableProperty[T] with WritableProperty[T] {

  def bindBidirectional(other: Property[T]): Unit = {
    checkParametersForBinding(other)
    doBidirectionalBinding(other)
  }

  def doBidirectionalBinding(other: Property[T]): Unit

  override val self: Property[T] = this

}