package cl.uchile.dcc.citric
package observer

/** Represents an Observer.
 *
 *  An observer should have a method to update every time
 *  the subject notifies some change.
 * */
trait Observer[T] {

    /* Does something depending on the subject and the message. */
    def update(subject: ISubject[T], msg: T): Unit
}
