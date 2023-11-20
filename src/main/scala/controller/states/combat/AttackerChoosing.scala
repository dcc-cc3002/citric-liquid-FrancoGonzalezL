package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.GameState

class AttackerChoosing(controller: GameController) extends GameState(controller) {

    override def attack(): Unit = {
        /* do something */
        this.changeState(new DefenderChoosing(controller))
    }
}
