package cl.uchile.dcc.citric
package observer

/** The trait for a generic Subject
 *
 *  A subject should be able to add new observer and notify them.
 * */
trait ISubject[T] {

    /* Adds new observers. */
    def registerObserver(o: Observer[T]): Unit

    /* Notifies all observers. */
    def notifyObservers(response: T): Unit
}
