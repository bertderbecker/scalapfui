package io.github.bertderbecker.scalapfui

class RunLater(op: () => Unit)
  extends Modifier[Any, Nothing](_ => op.apply()) {

  def run(): Unit = op.apply()
}

object RunLater {
  def apply(op: () => Unit) = new RunLater(op)
}