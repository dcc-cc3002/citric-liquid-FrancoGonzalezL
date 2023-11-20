package cl.uchile.dcc.citric
package controller.states.startAndEnd

import controller.GameController
import controller.states.{Chapter, GameState}

class PreGame(controller: GameController) extends GameState(controller) {
    override def startGame(): Unit = {
        /* Do something */
        this.changeState(new Chapter(controller))
    }
    override def isStarting(): Boolean = true
}
