package cl.uchile.dcc.citric
package controller.states.panel

import controller.GameController
import controller.states.GameState

import cl.uchile.dcc.citric.controller.states.combat.AttackerChoosingPvP

class LandingPanel(controller: GameController) extends GameState {

    override def pass(): Unit = {
        /* do something */
        this.changeState(controller, new PanelEffect(controller))
    }

    override def choosePlayer(): Unit = {
        /* do something */
        this.changeState(controller, new AttackerChoosingPvP(controller))
    }


}
