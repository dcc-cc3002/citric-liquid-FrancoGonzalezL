package cl.uchile.dcc.citric
package controller.states

import controller.GameController
import controller.states.startAndEnd.EndGame
import controller.states.playerTurn.{PlayerTurn, Recovery}

/** Controls the Chapters of the Game.
 *
 *  From this state:
 *  - If a player has achieved norma lvl 6, the Game can be finished.
 *  - If all player has played their turn, advance to the next chapter.
 *  - A player can begin its turn or recover.
 */
class Chapter(controller: GameController) extends GameState(controller) {
    override def play(): Unit = {

    }

    override def finishGame(): Unit = {
        /* do something */
        this.changeState(new EndGame(controller))
    }

    override def playTurn(): Unit = {
        /* do something */
        this.changeState(new PlayerTurn(controller))
    }

    override def recoverPlayer(): Unit = {
        /* do something */
        this.changeState(new Recovery(controller))
    }

    override def nextChapter(): Unit = {
        /* do something */
        this.changeState(new Chapter(controller))
    }
}
