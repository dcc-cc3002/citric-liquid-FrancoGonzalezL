package cl.uchile.dcc.citric
package controller.states.startAndEnd

import controller.GameController
import controller.states.GameState

class EndGame(controller: GameController) extends GameState {

    override def playAgain(): Unit = {
        /* do something */
        this.changeState(controller, new PreGame(controller))
    }

    override def hasFinished(): Boolean = true
}