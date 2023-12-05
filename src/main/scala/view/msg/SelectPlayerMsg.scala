package cl.uchile.dcc.citric
package view.msg

import model.unit.player.IPlayer

class SelectPlayerMsg(currentPlayer: IPlayer, options: Array[IPlayer]) extends ADisplayable {
    override def max: Int = options.length + 1

    override def toString: String = {
        var msg: String =
            s"""${currentPlayer.name}
               |Select a player to attack:
               |1 -> Continue
               |""".stripMargin

        for((player, index) <- options.zipWithIndex){
           msg +=  s"${index + this.min} -> ${player.toString} \n"
        }

        msg
    }
}
