package io.github.bertderbecker.scalapfui

trait Element[Native] {

  val pure: () => Native

  val modifiers: Seq[Modifier[_, Native]]

  def render: Native = {
    val obj: Native = pure()
    for (modifier <- modifiers) {
      modifier.applyTo(obj)
    }
    obj
  }

}
