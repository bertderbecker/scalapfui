package io.github.bertderbecker.scalapfui

import scala.language.higherKinds

trait ParentTag[Native <: BaseParent, BaseElement, BaseParent] {

  type ElementImpl[N]
  type ParentImpl[N <: BaseParent]

  def apply(children: ElementImpl[_ <: BaseElement]*)(modifiers: Modifier[_, Native]*): ParentImpl[Native]

}
