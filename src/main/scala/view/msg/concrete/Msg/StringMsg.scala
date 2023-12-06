package cl.uchile.dcc.citric
package view.msg.concrete.Msg

import view.msg.ADisplayable

class StringMsg(msg: String) extends ADisplayable {
    override def max: Int = 1

    override def toString: String = msg
}
