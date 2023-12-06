package cl.uchile.dcc.citric
package view

import view.msg.Displayable
import cl.uchile.dcc.citric.view.msg.concrete.Msg.StringMsg

abstract class AView extends IView {

    protected def display(msg: Displayable): Unit

    protected def getInt(max: Int): Int

    protected def getString: String

    override def sendMsg(msg: Displayable): Unit = {
        this.display(msg)
    }

    override def receiveIntInput(msg: Displayable, default: Option[Int] = None): Int = {
        this.display(msg)

        val min: Int = msg.min
        val max: Int = msg.max

        val selected = this.getInt(max)

        if (selected < min || max < selected) {
            val incorrectInputMsg: String = s"Incorrect Input: should be  $min <= 'input' <= $max"
            sendMsg(new StringMsg(incorrectInputMsg))
            receiveIntInput(msg)

        } else
            selected
    }

    override def receiveStringInput(msg: Displayable): String = {
        this.display(msg)
        this.getString
    }

}
