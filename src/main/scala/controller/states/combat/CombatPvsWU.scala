package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.Chapter
import model.panel.Panel
import cl.uchile.dcc.citric.model.panel.concretePanel.EncounterPanel

/** Represent a Combat between a Player and a Wild Unit.
 *
 *  The combats will continue until one of the units is defeated.
 */
class CombatPvsWU(controller: GameController) extends ACombat(controller){

    override def play(): Unit = {
        val panel: Panel = controller.currentPanel
        if (panel.wildUnit.isDefined)
            this.battle(panel.wildUnit.get)

        nextTurn()
    }

    override def nextTurn(): Unit = this.changeState(new Chapter(controller))
}

