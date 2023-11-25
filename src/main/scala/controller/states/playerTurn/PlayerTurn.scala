package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.GameState

/** The Player Turns.
 *
 *  On this state:
 *  - Receive stars.
 *  - Let the player roll the Dice to move.
 */
class PlayerTurn(controller: GameController) extends GameState(controller) {

    override def rollDice(): Unit = {
        /* do something */
        this.changeState(new Moving(controller))
    }
}
