package io.github.bertderbecker.scalapfui

trait ParentTag[Native, BaseElement] {
  def apply(children: Element[_ <: BaseElement]*)(modifiers: Modifier[_, Native]*): Parent[Native]

}
