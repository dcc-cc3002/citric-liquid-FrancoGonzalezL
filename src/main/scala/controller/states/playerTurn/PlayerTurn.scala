package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.{GameState, LandingPanel}
import model.unit.player.IPlayer
import model.panel.Panel

import cl.uchile.dcc.citric.controller.states.combat.CombatPvsP
import cl.uchile.dcc.citric.model.panel.home.Home

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

        moves = player.rollDice()
        while (moves > 0) {
            moving()
            moves -= 1
        }
        this.stop()
    }

    override def stop(): Unit = {
        moves = 0
        changeState(new CombatPvsP(controller))
    }

    private def moving(): Unit = {
        val panel: Panel = controller.currentPanel
        val player: IPlayer = controller.currentCharacter

        if(panel.canStopHere(player) && wantsToStop()) {
            stop()
        } else if(panel.nextPanelsCount == 1){
            val nextPanel: Panel = panel.nextPanels.head
            controller.moveCharacterToPanel(nextPanel)
        } else {
            selectPath()
        }
    }

    private def selectPath(): Unit = {
        val panel: Panel = controller.currentPanel
        val init: Int = 1
        var pathMsg: String = s"""${controller.currentCharacter.name}
                                   |Select a panel to continue:
                                   |""".stripMargin
        pathMsg += panel.nextPanelsToString(init)
        val selected: Int = controller.receiveInput(pathMsg, panel.nextPanelsCount) - init
        val nextPanel: Panel = panel.getNextPanelByIndex(selected)
        controller.moveCharacterToPanel(nextPanel)
    }

    private def wantsToStop(): Boolean = {
        val selected: Int = controller.receiveInput(homePanelMsg, homePanelOptions)
        if(selected == 1) true
        else false
    }

    private val homePanelMsg: String =
        s"""${controller.currentCharacter.name}
           |You are on a Home Panel, Do you want to stop?
           |1 -> Yes
           |2 -> No  """.stripMargin

    private val homePanelOptions: Int = 2
    private var moves: Int = 0
}
