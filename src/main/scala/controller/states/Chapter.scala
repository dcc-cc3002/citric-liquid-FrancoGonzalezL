package cl.uchile.dcc.citric
package controller.states

import controller.GameController
import controller.states.playerTurn.{PlayerTurn, Recovery}
import model.unit.player.IPlayer
import controller.states.startAndEnd.EndGame

/** Controls the Chapters of the Game.
 *
 *  From this state:
 *  - If a player has achieved norma lvl 6, the Game can be finished.
 *  - If all player has played their turn, advance to the next chapter.
 *  - A player can begin its turn or recover.
 */
class Chapter(controller: GameController) extends GameState(controller) {

    override def play(): Unit = {
        val player: IPlayer = controller.currentCharacter

        if (controller.winner.isDefined) {
            finishGame()
        } else if (controller.turn == 0) {
            controller.advanceChapter()
        } else if (player.isKO) {
            recoverPlayer()
        } else {
            playTurn()
        }
    }

    override def playTurn(): Unit = this.changeState(new PlayerTurn(controller))

    override def recoverPlayer(): Unit = this.changeState(new Recovery(controller))

    override def finishGame(): Unit = this.changeState(new EndGame(controller))

    controller.advanceTurn()
}
