package cl.uchile.dcc.citric
package controller.states.startAndEnd

import controller.GameController
import controller.states.GameState
import model.unit.player.IPlayer
import view.msg.WinnerMsg

/** Controls the end of the Game.
 *  Should clear all the variables
 *  to be able to initialize another Game.
 */
class EndGame(controller: GameController) extends GameState(controller) {

    override def play(): Unit = {
        if(controller.winner.isEmpty) throw new AssertionError("Winner undefined: The game can not end.")
        val winner: IPlayer = controller.winner.get
        controller.view.sendMsg(new WinnerMsg(winner, controller.chapter))
    }

    override def playAgain(): Unit = this.changeState(new PreGame(controller))

    override def hasFinished: Boolean = true
}