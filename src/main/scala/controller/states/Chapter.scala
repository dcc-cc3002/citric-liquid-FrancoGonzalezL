package cl.uchile.dcc.citric
package controller.states

import controller.GameController
import controller.states.startAndEnd.EndGame
import controller.states.playerTurn.{PlayerTurn, Recovery}

class Chapter(controller: GameController) extends GameState(controller) {

    override def finishGame(): Unit = {
        /* do something */
        this.changeState(new EndGame(controller))
    }

    override def play(): Unit = {
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
