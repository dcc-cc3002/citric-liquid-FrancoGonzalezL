package cl.uchile.dcc.citric
package observer

trait ISubject[T] {

    def registerObserver(o: Observer[T]): Unit

    def notifyObserver(o: Observer[T]): Unit
}
