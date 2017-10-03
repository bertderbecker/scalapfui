package io.github.bertderbecker.scalapfui.attribute

import io.github.bertderbecker.scalapfui.Modifier

trait UnobservableWritableAttribute[T, Native] {

  def :=(param: T): Modifier[T, Native]

}
