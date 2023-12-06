package cl.uchile.dcc.citric
package view

import view.msg.Displayable

/** The view that interacts with the user and sends data to the controller. */
trait IView {

    def sendMsg(msg: Displayable): Unit

    /** Return the input from the user.
     *
     * The value should be greater or equal than 0.
     *
     * @param msg The msg that will be displayed to the user.
     */
    def receiveIntInput(msg: Displayable, default: Option[Int] = None): Int

    /** Returns a String  */
    def receiveStringInput(msg: Displayable): String
}
