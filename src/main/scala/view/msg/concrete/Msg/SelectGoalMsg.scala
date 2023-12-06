package cl.uchile.dcc.citric
package view.msg.concrete.Msg

import view.msg.ADisplayable
import model.unit.player.IPlayer

class SelectGoalMsg(player: IPlayer) extends ADisplayable{

    override def max: Int = 2

    override def toString: String = {
        s"""${player.name} Select your Goal:
           |1 -> Stars
           |2 -> Victories
           |""".stripMargin
    }

}
