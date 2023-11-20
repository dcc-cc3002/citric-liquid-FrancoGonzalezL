package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.GameState

class AttackerChoosing(controller: GameController) extends GameState {

    override def attack(): Unit = {
        /* do something */
        this.changeState(controller, new DefenderChoosing(controller))
    }
}
