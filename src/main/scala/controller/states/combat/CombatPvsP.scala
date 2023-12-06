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

        /* If the player is the only one on a panel. */
        if(panel.charactersCount > 1) {
            /* To ensure that the player will not be in the options. */
            val options: Array[IPlayer] = panel.characters.filterNot(_ == controller.currentCharacter).toArray
            /* Returns a value between 1 and options.length */
            val selection = controller.view.receiveIntInput(new SelectPlayerMsg(controller.currentCharacter, options))

            /** selection == 1 means continue. */
            if (selection > 1)
                this.battle(options(selection - 2))
        }
        finishCombat()
    }

    override def finishCombat(): Unit = this.changeState(new LandingPanel(controller))
}