package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.GameState

import cl.uchile.dcc.citric.controller.states.panel.PanelEffect

class DefenderChoosingPvP(controller: GameController) extends GameState(controller) {

    override def evade(): Unit = {
        /* do something */
        this.changeState(new AttackerChoosingPvP(controller))
    }

    override def defend(): Unit = {
        /* do something */
        this.changeState(new AttackerChoosingPvP(controller))
    }

    override def deliverReward(): Unit = {
        /* do something */
        this.changeState(new PanelEffect(controller))
    }
}
