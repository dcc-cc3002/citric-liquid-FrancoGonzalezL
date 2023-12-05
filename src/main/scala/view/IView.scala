package cl.uchile.dcc.citric
package view

import view.msg.Displayable

trait IView {

    def sendMsg(msg: Displayable): Unit

    def receiveIntInput(msg: Displayable): Int

    def receiveStringInput(msg: Displayable): String
}
