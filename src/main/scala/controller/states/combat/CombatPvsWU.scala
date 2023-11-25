package cl.uchile.dcc.citric
package controller.states.combat

import controller.states.GameState
import controller.GameController
import controller.states.Chapter

/** Represent a Combat between a Player and a Wild Unit.
 *
 *  The combats will continue until one of the units is defeated.
 */
class CombatPvsWU(controller: GameController) extends GameState(controller){

    override def finishCombat(): Unit = {
        /* do something */
        this.changeState(new Chapter(controller))
    }
}

