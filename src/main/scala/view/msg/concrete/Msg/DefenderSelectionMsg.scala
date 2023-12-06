package cl.uchile.dcc.citric
package view.msg.concrete.Msg

import model.unit.IUnit
import view.msg.ADisplayable

class DefenderSelectionMsg(defender: IUnit) extends ADisplayable {

    override def max: Int = 2

    override def toString: String = {
        s"""${defender.name}
           |Select defense method:
           |1 -> Defend
           |2 -> Evade
           |""".stripMargin
    }
}
