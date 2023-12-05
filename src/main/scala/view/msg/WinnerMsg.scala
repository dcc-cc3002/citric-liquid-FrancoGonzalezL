package cl.uchile.dcc.citric
package view.msg

import model.unit.player.IPlayer

class WinnerMsg(winner: IPlayer, chapter: Int) extends ADisplayable {
    override def max: Int = 1

    override def toString: String = {
        s"""The Game has finish. Congratulations!!
           |
           |The winner is: ${winner.name}
           |Total Stars: ${winner.stars}
           |Total victories: ${winner.victories}
           |
           |The Game has ended with a Total of ${chapter} Chapters.
           |""".stripMargin
    }

}
