package io.github.bertderbecker.scalapfui.property

import java.lang.ref.WeakReference

private case class BidirectionalBinding[T](property1: Property[T],
                                   property2: Property[T],
                                   private var updating: Boolean = false) {

  private var propertyRef1: WeakReference[Property[T]] = new WeakReference(property1)
  private var propertyRef2: WeakReference[Property[T]] = new WeakReference(property2)

  val listener: (ReadableProperty[_ <: T], T, T) => Unit = { (sourceProperty, oldValue, newValue) =>
    if (!updating) {
      val property1 = propertyRef1.get
      val property2 = propertyRef2.get
      if ((property1 == null) || (property2 == null)) {
        if (property1 != null) property1.removeOnChange(listener)
        if (property2 != null) property2.removeOnChange(listener)
      }
      else try {
        updating = true
        if (property1 eq sourceProperty) property2.update(newValue) //TODO: is update right used? Or use doUpdate
        else property1.update(newValue)
      } catch {
        case e: RuntimeException =>
          try
              if (property1 eq sourceProperty) property1.update(oldValue)
              else property2.update(oldValue)
          catch {
            case e2: Exception =>
              e2.addSuppressed(e) //TODO: Unbind on failure
              throw new RuntimeException(
                "Bidirectional binding failed together with an attempt" +
                  " to restore the source property to the previous value." +
                  " Removing the bidirectional binding from properties " +
                  property1 + " and " + property2, e2
              )
          }
          throw new RuntimeException("Bidirectional binding failed, setting to the previous value", e)
      } finally updating = false
    }
  }
}
