package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.GameState
import model.unit.player.IPlayer
import model.panel.Panel

import controller.states.combat.CombatPvsP
import view.msg.{HomeMsg, PathMsg}

/** The Player Turns.
 *
 *  On this state:
 *  - Receive stars.
 *  - Let the player roll the Dice to move.
 */
class PlayerTurn(controller: GameController) extends GameState(controller) {

    override def play(): Unit = {
        val player: IPlayer = controller.currentCharacter

        val chapterStars: Int = (controller.chapter / 5) + 1
        player.stars += chapterStars

        movesLeft = player.rollDice()
        while (moving(player)) {
            movesLeft -= 1
        }
        this.stop()
    }

    private def moving(player: IPlayer): Boolean = {
        val panel: Panel = controller.currentPanel
        if (movesLeft < 1) return false

        if(panel.canStopHere(player) && wantsToStop(player)) {
            stop()
        } else if(panel.nextPanelsCount == 1){
            val nextPanel: Panel = panel.nextPanels.head
            controller.moveCharacterToPanel(nextPanel)
        } else {
            selectPath(player, panel)
        }

        movesLeft > 0
    }

    private def selectPath(player: IPlayer, panel: Panel): Unit = {
        val selected: Int = controller.view.receiveIntInput(new PathMsg(player, panel.nextPanels.toArray))
        val nextPanel: Panel = panel.nextPanels(selected)
        controller.moveCharacterToPanel(nextPanel)
    }

    private def wantsToStop(player: IPlayer): Boolean = {
        val selected: Int = controller.view.receiveIntInput(new HomeMsg(player))
        selected == 1
    }

    override def stop(): Unit = {
        movesLeft = 0
        changeState(new CombatPvsP(controller))
    }

    private var movesLeft: Int = 0
}
