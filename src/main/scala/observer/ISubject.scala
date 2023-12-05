package cl.uchile.dcc.citric
package observer

trait ISubject[T] {

    def registerObserver(o: Observer[T]): Unit

    def notifyObservers(response: T): Unit
}
