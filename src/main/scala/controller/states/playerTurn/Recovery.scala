package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.{Chapter, GameState}

class Recovery(controller: GameController) extends GameState {

    override def requirementsAchieved(): Unit = {
        /* do something */
        this.changeState(controller, new PlayerTurn(controller))
    }

    override def requirementsNotAchieved(): Unit = {
        /* do something */
        this.changeState(controller, new Chapter(controller))
    }
}

