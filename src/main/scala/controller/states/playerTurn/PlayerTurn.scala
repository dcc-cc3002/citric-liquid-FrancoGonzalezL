package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.GameState
import model.unit.player.IPlayer
import model.panel.Panel
import controller.states.combat.CombatPvsP
import cl.uchile.dcc.citric.view.msg.concrete.Msg.{HomeMsg, PathMsg, StringMsg}


/** This class represents the state of a player's turn in the game.
 *
 * During this state:
 * - The player receives stars based on the game's chapter.
 * - The player is prompted to roll the dice to move on the game board.
 *
 * @param controller The game controller that manages the flow of the game.
 */
class PlayerTurn(controller: GameController) extends GameState(controller) {

    override def play(): Unit = {
        val player: IPlayer = controller.currentCharacter
        controller.view.sendMsg(new StringMsg(s"${player.name} is your turn!!"))

        val chapterStars: Int = (controller.chapter / 5) + 1
        player.stars += chapterStars

        controller.view.receiveStringInput(new StringMsg(s"${player.name} roll the Dice"))
        movesLeft = player.rollDice()
        controller.view.sendMsg(new StringMsg(s"Yeah, you have ${movesLeft} moves."))
        initialMoves = movesLeft

        while (moving(player)) {
            movesLeft -= 1
        }
        this.stop()
    }

    /** Handles the movement of the player across the game board.
     *
     * @param player The current player taking the turn.
     * @return A boolean indicating if the player can continue moving.
     */
    private def moving(player: IPlayer): Boolean = {
        val panel: Panel = controller.currentPanel
        if (movesLeft < 1) return false

        if(panel.canStopHere(player) && (initialMoves > movesLeft) && wantsToStop(player)) {
            stop()
        } else if(panel.nextPanelsCount == 1){
            val nextPanel: Panel = panel.nextPanels.head
            controller.moveCharacterToPanel(nextPanel)
        } else {
            selectPath(player, panel)
        }

        movesLeft > 0
    }

    /** Allows the player to select a path when faced with multiple choices.
     *
     * @param player The player making the choice.
     * @param panel  The current panel the player is on.
     */
    private def selectPath(player: IPlayer, panel: Panel): Unit = {
        val selected: Int = controller.view.receiveIntInput(new PathMsg(player, panel.nextPanels.toArray))
        val nextPanel: Panel = panel.nextPanels(selected - 1)
        controller.moveCharacterToPanel(nextPanel)
    }

    /** Determines if the current panel is a Home Panel and the player
     * wishes to stop at it.
     *
     * @param player The player who may choose to stop.
     * @return A boolean indicating the player's decision to stop.
     */
    private def wantsToStop(player: IPlayer): Boolean = {
        val selected: Int = controller.view.receiveIntInput(new HomeMsg(player))
        selected == 1
    }

    override def stop(): Unit = {
        /* Sets moves to 0 in case the player has chosen to stop at a Home Panel. */
        movesLeft = 0
        changeState(new CombatPvsP(controller))
    }

    /* The number of moves the player has left in the turn. */
    private var movesLeft: Int = 0
    /* The initial number of moves after the player rolls the dice. */
    private var initialMoves: Int = 0
}
