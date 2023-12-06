package cl.uchile.dcc.citric
package view.msg.concrete.Msg

import model.panel.Panel
import model.unit.player.IPlayer
import view.msg.ADisplayable

class PathMsg(player: IPlayer, options: Array[Panel]) extends ADisplayable {
    override def max: Int = options.length

    override def toString: String = {
        var pathMsg: String =
            s"""${player.name}
               |Select a panel to continue:
               |""".stripMargin

        for ((panel, index) <- options.zipWithIndex) {
            pathMsg += s"${min + index} -> ${panel.toString} \n"
        }

        pathMsg
    }

}
