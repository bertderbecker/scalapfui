package io.github.bertderbecker.scalapfui.property

import scala.language.higherKinds

trait NestedReadableProperty[T, CC[_]] extends ReadableProperty[T] {

  val property: CC[T]

}