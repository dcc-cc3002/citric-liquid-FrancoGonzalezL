package cl.uchile.dcc.citric
package controller.states.panel

import controller.GameController
import controller.states.{Chapter, GameState}

import cl.uchile.dcc.citric.controller.states.combat.AttackerChoosing

class PanelEffect(controller: GameController) extends GameState(controller) {

    override def nextTurn(): Unit = {
        /* do something */
        this.changeState(new Chapter(controller))
    }

    override def encounterPanelEffect(): Unit = {
        /* do something */
        this.changeState(new AttackerChoosing(controller))
    }
}
