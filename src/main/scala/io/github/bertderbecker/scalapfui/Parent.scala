package io.github.bertderbecker.scalapfui

trait Parent[Native] extends Element[Native] {

  val children: Seq[Element[_]]

  def addChild[T](pure: Native, child: T): Unit

  override def render: Native = {
    val modified = super.render
    for (c <- children) addChild(modified, c.render)
    modified
  }
}
