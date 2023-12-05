package cl.uchile.dcc.citric
package view.msg

import model.unit.player.IPlayer

class HomeMsg(player: IPlayer) extends ADisplayable {
    override def max: Int = 2

    override def toString: String = {
        s"""${player.name}
           |You are on a Home Panel, Do you want to stop?
           |1 -> Yes
           |2 -> No
           |""".stripMargin
    }
}
