package cl.uchile.dcc.citric
package controller.states.startAndEnd

import controller.GameController
import controller.states.{Chapter, GameState}

/** Controls the Pre Game settings,
 *  should initialize all the variables to play the game.
 */
class PreGame(controller: GameController) extends GameState(controller) {

    override def startGame(debug: Boolean = false): Unit = {
        controller.debug_=(debug)
        this.changeState(new Chapter(controller))
    }

    override def isStarting: Boolean = true
}
