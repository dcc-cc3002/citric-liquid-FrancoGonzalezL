package cl.uchile.dcc.citric
package controller.states.startAndEnd

import controller.GameController
import controller.states.GameState
import model.unit.player.IPlayer

/** Controls the end of the Game.
 *  Should clear all the variables
 *  to be able to initialize another Game.
 */
class EndGame(controller: GameController) extends GameState(controller) {

    override def play(): Unit = {
        if(controller.winner.isEmpty) throw new AssertionError("Winner undefined: The game can not end.")
        val winner: IPlayer = controller.winner.get
        val msg: String =
            s"""The Game has finish. Congratulations!!
               |
               |The winner is: ${winner.name}
               |Total Stars: ${winner.stars}
               |Total victories: ${winner.victories}
               |
               |The Game has ended with a Total of ${controller.chapter} Chapters.""".stripMargin
        controller.sendMsg(msg)
    }

    override def playAgain(): Unit = this.changeState(new PreGame(controller))

    override def hasFinished: Boolean = true
}