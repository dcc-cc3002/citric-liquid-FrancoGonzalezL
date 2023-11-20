package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.GameState

class AttackerChoosingPvP(controller: GameController) extends GameState {

    override def attack(): Unit = {
        /* do something */
        this.changeState(controller, new DefenderChoosingPvP(controller))
    }
}