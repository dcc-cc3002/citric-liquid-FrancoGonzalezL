package cl.uchile.dcc.citric
package view.msg

class PlayAgainMsg extends ADisplayable {
    override def max: Int = 2

    override def toString: String = {
        "Play Again?\n"
    }
}
