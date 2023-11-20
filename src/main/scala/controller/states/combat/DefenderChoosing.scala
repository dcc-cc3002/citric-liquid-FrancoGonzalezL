package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.{Chapter, GameState}

class DefenderChoosing(controller: GameController) extends GameState(controller) {
    override def evade(): Unit = {
        /* do something */
        this.changeState(new AttackerChoosing(controller))
    }

    override def defend(): Unit = {
        /* do something */
        this.changeState(new AttackerChoosing(controller))
    }

    override def deliverReward(): Unit = {
        /* do something */
        this.changeState(new Chapter(controller))
    }
}
