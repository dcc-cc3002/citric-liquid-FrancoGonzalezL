package cl.uchile.dcc.citric
package controller.states.startAndEnd

import controller.GameController
import controller.states.GameState

class EndGame(controller: GameController) extends GameState(controller) {

    override def playAgain(): Unit = {
        /* do something */
        this.changeState(new PreGame(controller))
    }

    override def hasFinished(): Boolean = true
}