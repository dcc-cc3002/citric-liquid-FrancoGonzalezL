package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.GameState
import controller.states.panel.PanelEffect


/** Represent a Combat between a Player and another Player.
 *
 *  The combats will continue until one of the player is defeated.
 */
class CombatPvsP(controller: GameController) extends GameState(controller){

    override def finishCombat(): Unit = {
        /* Do something */
        this.changeState(new PanelEffect(controller))
    }
}