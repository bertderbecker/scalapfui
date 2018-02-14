package io.github.bertderbecker.scalapfui.attribute
import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.extras.Includes._
import io.github.bertderbecker.scalapfui.property.{ReadableProperty, WritableProperty}

trait WritableAttribute[T, Native] extends SimpleWritableAttribute[T, Native] {

  val writablePropertyExtractor: Native => WritableProperty[T]

  override def :=(param: T): Modifier[T, Native] = Modifier[T, Native] { native =>
    println("beginn :=")
    println("writablePropertyExtractor = " + writablePropertyExtractor)
    try {
      writablePropertyExtractor(native).update(param)
    } catch {
      case e: Exception =>
        println("voilà le stacktrace !!!")
        e.printStackTrace()
    }
    println("finish :=")
  }

  def <==[B <: T, N >: Native](readableAttribute: ReadableAttribute[B, N]): Modifier[B, Native] =
    bindTo[B, N](readableAttribute.readablePropertyExtr)

  def <==(readableProp: ReadableProperty[T]): Modifier[T, Native] = bindTo[T, Any]((_) => readableProp)

  def bindTo[B <: T, N >: Native](op: N => ReadableProperty[B]): Modifier[B, Native] =
    Modifier[B, Native] { native =>
      println("start modifiing binding")
      try {
        val e = writablePropertyExtractor(native)
        op(native).value.ifDefined(e.doUpdate)
        e.doBinding(op(native))
      } catch {
        case e: Exception => e.printStackTrace()
      }
      println("finish modifiing binding")
    }

}