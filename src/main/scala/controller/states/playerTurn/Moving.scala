package cl.uchile.dcc.citric
package controller.states.playerTurn

import controller.GameController
import controller.states.GameState

import controller.states.panel.LandingPanel

/** When the player has roll a Dice, the player has to move the amount obtained.
 *
 *  While moving:
 *  - The player can stop on a HomePanel if the player is the owner of the HomePanel.
 *  - The Player can choose a path if the Panel has multiple NextPanels.
 *  - If the player has no left movements then the player can not keep moving.
 */
class Moving(controller: GameController) extends GameState(controller) {

    override def choosePath(): Unit = {
        /* do something */
        this.changeState(new Moving(controller))
    }

    override def stop(): Unit = {
        /* do something */
        this.changeState(new LandingPanel(controller))
    }

    override def noMovementsLeft(): Unit = {
        /* do something */
        this.changeState(new LandingPanel(controller))
    }
}
