package cl.uchile.dcc.citric
package observer

class Subject[T] extends ISubject[T] {

    private var observers: Array[Observer[T]] = Array()

    override def registerObserver(o: Observer[T]): Unit = {
        observers = observers :+ o
    }

    override def notifyObservers(response: T): Unit = {
        observers.foreach(o => {
            o.update(this, response)
        })
    }
}
