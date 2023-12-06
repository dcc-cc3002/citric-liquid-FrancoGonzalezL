package cl.uchile.dcc.citric
package view

import view.msg.{Displayable, StringMsg}
import scala.io.StdIn

class View extends AView {

    override protected def getInt(max: Int): Int = {
        try {
            StdIn.readInt()
        }catch {
            case e: NumberFormatException =>
                display(new StringMsg("The input should be a number."))
                getInt(max: Int)
        }
    }

    override protected def getString: String = {
        StdIn.readLine()
    }

    override protected def display(msg: Displayable): Unit = {
        println(msg)
    }
}
