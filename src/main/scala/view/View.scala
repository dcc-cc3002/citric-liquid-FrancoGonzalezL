package cl.uchile.dcc.citric
package view

import view.msg.Displayable
import scala.io.StdIn

class View extends AView {

    override protected def getInt(max: Int): Int = {
        StdIn.readInt()
    }

    override protected def getString: String = {
        StdIn.readLine()
    }

    override protected def display(msg: Displayable): Unit = {
        print(msg)
    }
}
