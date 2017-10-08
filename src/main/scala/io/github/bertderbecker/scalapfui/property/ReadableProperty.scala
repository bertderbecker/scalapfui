package io.github.bertderbecker.scalapfui.property

import io.github.bertderbecker.scalapfui.Math.Calculatable


trait ReadableProperty[T] extends Calculatable[T, ReadableProperty] {

  def calcValue: Option[T]

  def processOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit

  def removeOnChange(op: (ReadableProperty[T], T, T) => Unit): Unit


  def onChange(op: T => Unit): Unit =
    processOnChange((_, _, newValue) => op(newValue))

  def onChangeFull(op: (ReadableProperty[T], T, T) => Unit): Unit = processOnChange(op)

  def apply(): Option[T] = value

  def value: Option[T] = calcValue

  override val self: ReadableProperty[T] = this

}