package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.{GameState, LandingPanel}

import model.panel.Panel
import model.unit.IUnit
import model.unit.player.IPlayer
import view.msg.{DefenderSelectionMsg, SelectPlayerMsg, StringMsg}


/** Represent a Combat between a Player and another Player.
 *
 *  The combats will continue until one of the player is defeated.
 */
class CombatPvsP(controller: GameController) extends ACombat(controller){

    override def play(): Unit = {
        val panel: Panel = controller.currentPanel

        val selection = controller.view.receiveIntInput(new SelectPlayerMsg(controller.currentCharacter, panel.characters.toArray))
        if(selection == 1)
            finishCombat()
        else
            this.battle(panel.characters(selection))
    }

    override def finishCombat(): Unit = this.changeState(new LandingPanel(controller))
}