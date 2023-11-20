package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.GameState

class AttackerChoosingPvP(controller: GameController) extends GameState(controller) {

    override def attack(): Unit = {
        /* do something */
        this.changeState(new DefenderChoosingPvP(controller))
    }
}