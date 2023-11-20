package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.{Chapter, GameState}

class DefenderChoosing(controller: GameController) extends GameState {
    override def evade(): Unit = {
        /* do something */
        this.changeState(controller, new AttackerChoosing(controller))
    }

    override def defend(): Unit = {
        /* do something */
        this.changeState(controller, new AttackerChoosing(controller))
    }

    override def deliverReward(): Unit = {
        /* do something */
        this.changeState(controller, new Chapter(controller))
    }
}
