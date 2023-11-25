package cl.uchile.dcc.citric
package controller.states.startAndEnd

import controller.GameController
import controller.states.GameState

/** Controls the end of the Game.
 *  Should clear all the variables
 *  to be able to initialize another Game.
 */
class EndGame(controller: GameController) extends GameState(controller) {

    override def playAgain(): Unit = {
        /* do something */
        this.changeState(new PreGame(controller))
    }

    override def hasFinished(): Boolean = true
}