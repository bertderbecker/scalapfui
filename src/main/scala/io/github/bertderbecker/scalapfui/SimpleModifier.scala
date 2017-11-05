package io.github.bertderbecker.scalapfui

case class SimpleModifier(op: () => Unit) {

  def apply(): Unit = op.apply()

}
