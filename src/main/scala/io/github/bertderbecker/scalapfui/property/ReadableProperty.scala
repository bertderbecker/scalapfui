package io.github.bertderbecker.scalapfui.property

import io.github.bertderbecker.scalapfui.Math.Calculatable


trait ReadableProperty[T] extends Calculatable[T, ReadableProperty] {

  private[scalapfui] def calcValue: Option[T]

  private[scalapfui] def processOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit

  private[scalapfui] def removeOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit


  final def onChange(op: T => Unit): Unit =
    processOnChange((_, _, newValue) => op(newValue))

  final def onChangeFull(op: (ReadableProperty[T], T, T) => Unit): Unit = processOnChange(op)

  final def apply(): Option[T] = value

  final def value: Option[T] = calcValue

  override val self: ReadableProperty[T] = this

}