package cl.uchile.dcc.citric
package view

import view.msg.Displayable

import util.Random

class NullView extends AView {

    override def receiveIntInput(msg: Displayable, default: Option[Int] = None): Int = {
        if(default.isDefined)
            default.get
        else
            super.receiveIntInput(msg)
    }

    private val random: Random = new Random()

    override protected def getInt(max: Int): Int = {
        // returns random input
        random.nextInt(max) + 1
    }

    override protected def getString: String = {
        random.nextString(5)
    }

    override protected def display(msg: Displayable): Unit = {
        // Does Nothing
    }
}
