package cl.uchile.dcc.citric
package view.msg.concrete.Msg

import model.unit.player.IPlayer
import view.msg.ADisplayable

class SelectPlayerMsg(currentPlayer: IPlayer, options: Array[IPlayer]) extends ADisplayable {
    override def max: Int = options.length + 2

    override def toString: String = {
        var msg: String =
            s"""${currentPlayer.name}
               |Select a player to attack:
               |1 -> Continue
               |""".stripMargin

        for((player, index) <- options.zipWithIndex){
           msg +=  s"${index + this.min + 1} -> ${player.toString} \n"
        }

        msg
    }
}
