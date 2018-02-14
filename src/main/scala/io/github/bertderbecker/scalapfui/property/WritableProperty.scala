package io.github.bertderbecker.scalapfui.property

import com.github.ghik.silencer.silent
import io.github.bertderbecker.scalapfui.extras.Includes._


trait WritableProperty[-T] {

  def doUpdate(newValue: T): Unit

  private[scalapfui] def doBinding[B <: T](other: ReadableProperty[B]): Unit =
    other.onChange(doUpdate)

  final private var binded: Boolean = false

  final def bindTo[B <: T](other: ReadableProperty[B]): Unit = {
    checkParametersForBinding(other)
    doBinding(other)
    other.value.ifDefined(update)
    binded = true
  }

  final def <==[B <: T](other: ReadableProperty[B]): Unit = bindTo(other)

  @silent
  protected def checkParametersForBinding[B <: T](other: ReadableProperty[B]): Unit = {
    if (this == null || other == null)
      throw new NullPointerException("Both properties must be specified.")
    if (this eq other) throw new IllegalArgumentException("Cannot bind property to itself")
  }

  final def update(newValue: T): Unit =
    if (binded) throw new IllegalAccessException("Property is binded")
    else {
      println("begin update")
      doUpdate(newValue)
      println("finish update")
    }

}