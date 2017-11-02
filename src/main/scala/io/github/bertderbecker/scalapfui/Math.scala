package io.github.bertderbecker.scalapfui

import cats.{FlatMap, Functor, Monoid, Semigroup}

import scala.language.higherKinds

object Math {

  trait Calculatable[T, Self[B] <: Calculatable[B, Self]] {

    val self: Self[T]

    def +(other: T)(implicit addable: Addable[T], functor: Functor[Self]): Self[T] =
      functor.map(self)(addable.combine(_, other))

    def -(other: T)(implicit substractable: Substractable[T], functor: Functor[Self]): Self[T] =
      functor.map(self)(substractable.combine(_, other))

    def *(other: T)(implicit multiplicatable: Multiplicatable[T], functor: Functor[Self]): Self[T] =
      functor.map(self)(multiplicatable.combine(_, other))

    def /(other: T)(implicit dividable: Dividable[T], functor: Functor[Self]): Self[T] =
      functor.map(self)(dividable.combine(_, other))


    def +(other: Self[T])(implicit addable: Addable[T], flatMap: FlatMap[Self]): Self[T] =
      flatMap.flatMap(self)(a => flatMap.map(other)(b => addable.combine(a, b)))

    def -(other: Self[T])(implicit substractable: Substractable[T], flatMap: FlatMap[Self]): Self[T] =
      flatMap.flatMap(self)(a => flatMap.map(other)(b => substractable.combine(a, b)))

    def *(other: Self[T])(implicit multiplicatable: Multiplicatable[T], flatMap: FlatMap[Self]): Self[T] =
      flatMap.flatMap(self)(a => flatMap.map(other)(b => multiplicatable.combine(a, b)))

    def /(other: Self[T])(implicit dividable: Dividable[T], flatMap: FlatMap[Self]): Self[T] =
      flatMap.flatMap(self)(a => flatMap.map(other)(b => dividable.combine(a, b)))

  }

  trait Addable[T] extends Semigroup[T]

  trait Substractable[T] extends Semigroup[T]

  trait Multiplicatable[T] extends Semigroup[T]

  trait Dividable[T] extends Semigroup[T]

  object Addable {
    def apply[T](add: (T, T) => T): Addable[T] = new Addable[T] {
      override def combine(x: T, y: T) = add.apply(x, y)
    }
  }

  object Substractable {
    def apply[T](substract: (T, T) => T): Substractable[T] = new Substractable[T] {
      override def combine(x: T, y: T) = substract.apply(x, y)
    }
  }

  object Multiplicatable {
    def apply[T](multiplicate: (T, T) => T): Multiplicatable[T] = new Multiplicatable[T] {
      override def combine(x: T, y: T) = multiplicate.apply(x, y)
    }
  }

  object Dividable {
    def apply[T](divide: (T, T) => T): Dividable[T] = new Dividable[T] {
      override def combine(x: T, y: T) = divide.apply(x, y)
    }
  }


  case class Empty[T](empty: T)

  trait MathInstances {

    private def genMonoid[T: Empty](combineVals: (T, T) => T) = new Monoid[T] {

      override def empty: T = implicitly[Empty[T]].empty

      override def combine(x: T, y: T): T = combineVals(x, y)
    }

    implicit def addableMonoid[T: Addable : Empty]: Monoid[T] = genMonoid(implicitly[Addable[T]].combine)

    implicit def substractableMonoid[T: Substractable : Empty]: Monoid[T] = genMonoid(implicitly[Substractable[T]].combine)

    implicit def multiplicatableMonoid[T: Multiplicatable : Empty]: Monoid[T] = genMonoid(implicitly[Multiplicatable[T]].combine)

    implicit def dividableMonoid[T: Dividable : Empty]: Monoid[T] = genMonoid(implicitly[Dividable[T]].combine)

    implicit val intAddable: Addable[Int] = Addable((a: Int, b: Int) => a + b)
    implicit val byteAddable: Addable[Byte] = Addable((a: Byte, b: Byte) => (a + b).asInstanceOf)
    implicit val shortAddable: Addable[Short] = Addable((a: Short, b: Short) => (a + b).asInstanceOf)
    implicit val longAddable: Addable[Long] = Addable((a: Long, b: Long) => a + b)
    implicit val floatAddable: Addable[Float] = Addable((a: Float, b: Float) => a + b)
    implicit val doubleAddable: Addable[Double] = Addable((a: Double, b: Double) => a + b)

    implicit val intSubstractable: Substractable[Int] = Substractable((a: Int, b: Int) => a - b)
    implicit val byteSubstractable: Substractable[Byte] = Substractable((a: Byte, b: Byte) => (a - b).asInstanceOf)
    implicit val shortSubstractable: Substractable[Short] = Substractable((a: Short, b: Short) => (a - b).asInstanceOf)
    implicit val longSubstractable: Substractable[Long] = Substractable((a: Long, b: Long) => a - b)
    implicit val floatSubstractable: Substractable[Float] = Substractable((a: Float, b: Float) => a - b)
    implicit val doubleSubstractable: Substractable[Double] = Substractable((a: Double, b: Double) => a - b)

    implicit val intMultiplicatable: Multiplicatable[Int] = Multiplicatable((a: Int, b: Int) => a * b)
    implicit val byteMultiplicatable: Multiplicatable[Byte] = Multiplicatable((a: Byte, b: Byte) => (a * b).asInstanceOf)
    implicit val shortMultiplicatable: Multiplicatable[Short] = Multiplicatable((a: Short, b: Short) => (a * b).asInstanceOf)
    implicit val longMultiplicatable: Multiplicatable[Long] = Multiplicatable((a: Long, b: Long) => a * b)
    implicit val floatMultiplicatable: Multiplicatable[Float] = Multiplicatable((a: Float, b: Float) => a * b)
    implicit val doubleMultiplicatable: Multiplicatable[Double] = Multiplicatable((a: Double, b: Double) => a * b)

    implicit val intDividable: Dividable[Int] = Dividable((a: Int, b: Int) => a / b)
    implicit val byteDividable: Dividable[Byte] = Dividable((a: Byte, b: Byte) => (a / b).asInstanceOf)
    implicit val shortDividable: Dividable[Short] = Dividable((a: Short, b: Short) => (a / b).asInstanceOf)
    implicit val longDividable: Dividable[Long] = Dividable((a: Long, b: Long) => a / b)
    implicit val floatDividable: Dividable[Float] = Dividable((a: Float, b: Float) => a / b)
    implicit val doubleDividable: Dividable[Double] = Dividable((a: Double, b: Double) => a / b)


  }

}
