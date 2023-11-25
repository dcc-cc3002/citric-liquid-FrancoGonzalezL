package cl.uchile.dcc.citric
package controller.states.panel

import controller.GameController
import controller.states.{Chapter, GameState}

import controller.states.combat.CombatPvsWU

/** When a player lands on a Panel, the Panel has to active its effect on the player.
 */
class PanelEffect(controller: GameController) extends GameState(controller) {

    override def nextTurn(): Unit = {
        /* do something */
        this.changeState(new Chapter(controller))
    }

    override def encounterPanelEffect(): Unit = {
        /* do something */
        this.changeState(new CombatPvsWU(controller))
    }
}
