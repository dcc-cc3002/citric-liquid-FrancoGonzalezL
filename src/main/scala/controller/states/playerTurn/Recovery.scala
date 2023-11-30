package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.{Chapter, GameState}
import model.unit.player.IPlayer

/** When a player is K.O, the player has to recover before playing.
 */
class Recovery(controller: GameController) extends GameState(controller) {

    override def play(): Unit = {
        val player: IPlayer = controller.currentCharacter
        val required: Int = math.max(0, 6 - controller.chapter)

        player.recovery(required)

        if (!player.isKO)
            playTurn()
        else
            nextTurn()
    }

    override def nextTurn(): Unit = {
        controller.rotateCharacters()
        changeState(new Chapter(controller))
    }

    override def playTurn(): Unit = {
        changeState(new PlayerTurn(controller))
    }
}