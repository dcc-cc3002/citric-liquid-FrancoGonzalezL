package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.GameState

import cl.uchile.dcc.citric.controller.states.panel.LandingPanel

class Moving(controller: GameController) extends GameState(controller) {

    override def choosePath(): Unit = {
        /* do something */
        this.changeState(new Moving(controller))
    }

    override def stop(): Unit = {
        /* do something */
        this.changeState(new LandingPanel(controller))
    }

    override def noMovementsLeft(): Unit = {
        /* do something */
        this.changeState(new LandingPanel(controller))
    }
}
