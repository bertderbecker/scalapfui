package io.github.bertderbecker.scalapfui.extras

/**
  * Created by erik on 09.07.17.
  */
trait OptionUnits {

  implicit class OptionUnitsOptionOps[T](option: Option[T]) {

    def ifDefined(u: (T) => Unit): Unit = if (option.isDefined) u(option.get)
  }

}
