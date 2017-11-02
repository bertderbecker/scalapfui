package io.github.bertderbecker.scalapfui

import scala.language.higherKinds

trait ElementTag[Native] {

  type ElementImpl[N]

  def apply(modifiers: Modifier[_, Native]*): ElementImpl[Native]
}
