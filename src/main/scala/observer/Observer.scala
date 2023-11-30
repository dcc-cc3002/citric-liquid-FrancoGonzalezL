package cl.uchile.dcc.citric
package observer

trait Observer[T] {

    def update(subject: ISubject[T], msg: T): Unit
}
