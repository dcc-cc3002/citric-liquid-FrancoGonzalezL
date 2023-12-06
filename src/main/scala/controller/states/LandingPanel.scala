package cl.uchile.dcc.citric
package controller.states

import controller.GameController
import controller.states.combat.{CombatPvsP, CombatPvsWU}
import model.panel.Panel
import model.unit.player.IPlayer

/** When landing on a Panel, the player can choose to attack another
 *  player or continue with the Game.
 */
class LandingPanel(controller: GameController) extends GameState(controller) {

    override def play(): Unit = {
        val panel: Panel = controller.currentPanel
        val player: IPlayer = controller.currentCharacter

        panel.apply(player)
        encounter()
    }

    override def encounter(): Unit = this.changeState(new CombatPvsWU(controller))
}
