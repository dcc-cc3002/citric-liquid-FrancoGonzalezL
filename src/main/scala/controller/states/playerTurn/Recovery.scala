package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.{Chapter, GameState}

class Recovery(controller: GameController) extends GameState(controller) {

    override def requirementsAchieved(): Unit = {
        /* do something */
        this.changeState(new PlayerTurn(controller))
    }

    override def requirementsNotAchieved(): Unit = {
        /* do something */
        this.changeState(new Chapter(controller))
    }
}

