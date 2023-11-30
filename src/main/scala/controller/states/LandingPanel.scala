package cl.uchile.dcc.citric
package controller.states.panel

import controller.GameController
import controller.states.GameState

import controller.states.combat.CombatPvsP

/** When landing on a Panel, the player can choose to attack another
 *  player or continue with the Game.
 */
class LandingPanel(controller: GameController) extends GameState(controller) {

    override def pass(): Unit = {
        /* do something */
        this.changeState(new PanelEffect(controller))
    }

    override def choosePlayer(): Unit = {
        /* do something */
        this.changeState(new CombatPvsP(controller))
    }


}
