package io.github.bertderbecker.scalapfui.attribute
import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.extras.Includes._
import io.github.bertderbecker.scalapfui.property.{ReadableProperty, WritableProperty}

trait WritableAttribute[T, Native] extends SimpleWritableAttribute[T, Native] {

  def writablePropertyExtractor: Native => WritableProperty[T]

  override def :=(param: T): Modifier[T, Native] = Modifier[T, Native] { native =>
    writablePropertyExtractor(native).update(param)
  }

  def <==(readableAttribute: ReadableAttribute[T, Native]): Modifier[T, Native] = bindTo(readableAttribute.readablePropertyExtr)

  def <==(readableProp: ReadableProperty[T]): Modifier[T, Native] = bindTo(_ => readableProp)

  def bindTo(op: Native => ReadableProperty[T]): Modifier[T, Native] = {

    val res = Modifier[T, Native] { native =>
      val e = writablePropertyExtractor(native)
      op(native).value.ifDefined(e.doUpdate)
      e.doBinding(op(native))
    }
    res
  }

}