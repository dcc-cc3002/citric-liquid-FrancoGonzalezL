package cl.uchile.dcc.citric
package controller.states

import controller.{GameChecks, GameController, GameTransitions}
import exceptions.InvalidTransitionException


/** Represents a state of the Game.
 *
 *  This state has no possible transitions
 *  and all the checks are set to false.
 *
 *  @param controller The Game Controller.
 */
class GameState(controller: GameController) extends GameTransitions with GameChecks {

    /** This should be used to change the state of the Game.
     *
     *  @param state The new State of the Game.
     */
    protected def changeState(state: GameState): Unit = {
        controller.setState(state)
    }

    /** This functions contains all the tasks the controller must perform while in this state.
     *
     *  Calling this function will simulate one step in the Game.
     */
    def play(): Unit = throw new AssertionError(s"Play() is Not Defined for ${this.getClass.getSimpleName}")

    /** The exception that should be thrown every time an invalid transition is attempted.
     *
     *  @param from The name of the State from which the transition is attempted.
     */
    private def exception(from: String) = throw new InvalidTransitionException(s"From $from")

    def startGame(): Unit = exception(this.getClass.getSimpleName)

    def finishGame(): Unit = exception(this.getClass.getSimpleName)

    def recoverPlayer(): Unit = exception(this.getClass.getSimpleName)

    def playTurn(): Unit = exception(this.getClass.getSimpleName)

    def stop(): Unit = exception(this.getClass.getSimpleName)

    def finishCombat(): Unit = exception(this.getClass.getSimpleName)

    def encounter(): Unit = exception(this.getClass.getSimpleName)

    def nextTurn(): Unit = exception(this.getClass.getSimpleName)

    def playAgain(): Unit = exception(this.getClass.getSimpleName)

    def isStarting: Boolean = false

    def hasFinished: Boolean = false
}