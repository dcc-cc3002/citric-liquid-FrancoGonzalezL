package cl.uchile.dcc.citric
package controller.states

import controller.GameController
import controller.states.combat.CombatPvsP
import model.panel.Panel
import model.unit.player.IPlayer
import model.panel.encounter.Encounter

/** When landing on a Panel, the player can choose to attack another
 *  player or continue with the Game.
 */
class LandingPanel(controller: GameController) extends GameState(controller) {

    override def play(): Unit = {
        val panel: Panel = controller.currentPanel
        val player: IPlayer = controller.currentCharacter

        panel.apply(player)
        panel match {
            case p: Encounter  => encounterPanel()
            case p: Panel => nextTurn()
        }
    }

    override def nextTurn(): Unit = {
        controller.rotateCharacters()
        this.changeState(new Chapter(controller))
    }

    override def encounterPanel(): Unit = this.changeState(new CombatPvsP(controller))


}
