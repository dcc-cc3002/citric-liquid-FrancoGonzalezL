package cl.uchile.dcc.citric
package view.msg.concrete.Msg

import view.msg.ADisplayable

class PlayAgainMsg extends ADisplayable {
    override def max: Int = 2

    override def toString: String = {
        "Play Again?\n"
    }
}
