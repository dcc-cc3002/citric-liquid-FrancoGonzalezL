package cl.uchile.dcc.citric
package observer

class Subject[T] extends ISubject[T] {

    override def registerObserver(o: Observer[T]): Unit = {}

    override def notifyObserver(o: Observer[T]): Unit = {}

}
