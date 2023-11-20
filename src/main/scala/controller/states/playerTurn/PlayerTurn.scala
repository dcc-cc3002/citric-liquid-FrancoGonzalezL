package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.GameState

class PlayerTurn(controller: GameController) extends GameState(controller) {

    override def rollDice(): Unit = {
        /* do something */
        this.changeState(new Moving(controller))
    }
}
