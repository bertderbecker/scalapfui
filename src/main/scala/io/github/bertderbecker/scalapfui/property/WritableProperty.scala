package io.github.bertderbecker.scalapfui.property

import com.github.ghik.silencer.silent
import io.github.bertderbecker.scalapfui.extras.Includes._


trait WritableProperty[T] {

  final private var binded: Boolean = false

  def doUpdate(newValue: T): Unit

  final def bindTo(other: ReadableProperty[T]): Unit = {
    checkParametersForBinding(other)
    doBinding(other)
    other.value.ifDefined(doUpdate)
    binded = true
  }

  @silent
  protected def checkParametersForBinding(other: ReadableProperty[T]): Unit = {
    if (this == null || other == null) throw new NullPointerException("Both properties must be specified.")
    if (this eq other) throw new IllegalArgumentException("Cannot bind property to itself")
  }

  def doBinding(other: ReadableProperty[T]): Unit = other.onChange(doUpdate)

  final def update(newValue: T): Unit =
    if (binded) throw new IllegalAccessException("Property is binded")
    else doUpdate(newValue)

}