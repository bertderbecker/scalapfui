package io.github.bertderbecker.scalapfui

trait ParentTag[Native, BaseElement] {
  def apply(modifiers: Modifier[_, Native]*)(children: Element[_ <: BaseElement]*): Parent[Native]

}
