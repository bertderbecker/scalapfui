package io.github.bertderbecker.scalapfui.attribute
import io.github.bertderbecker.scalapfui.Modifier
import io.github.bertderbecker.scalapfui.property.WritableProperty

trait WritableAttribute[T, Native] extends UnobservableWritableAttribute[T, Native] {

  def writablePropertyExtractor: Native => WritableProperty[T]

  override def :=(param: T): Modifier[T, Native] = Modifier[T, Native] { native =>
    writablePropertyExtractor(native).update(param)
  }

  def <==(readableAttribute: ReadableAttribute[T, Native]): Modifier[T, Native] = {

    val res = Modifier[T, Native] { native =>
      writablePropertyExtractor(native)
        .doBinding(readableAttribute.readablePropertyExtr(native))
    }
    res
  }
}