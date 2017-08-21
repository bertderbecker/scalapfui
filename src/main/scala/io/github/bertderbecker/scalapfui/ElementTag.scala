package io.github.bertderbecker.scalapfui

trait ElementTag[Native] {

  def apply(modifiers: Modifier[_, Native]*): Element[Native]
}
