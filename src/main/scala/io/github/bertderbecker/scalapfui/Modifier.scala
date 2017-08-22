package io.github.bertderbecker.scalapfui

case class Modifier[T, -Native](apply: Native => Unit) {

  def mapApply[V](op: V => Native) =
    new Modifier[T, V](native => applyTo(op(native)))

  def applyTo(pure: Native): Unit = {
    apply(pure)
  }

}
