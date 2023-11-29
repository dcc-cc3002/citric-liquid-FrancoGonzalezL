package cl.uchile.dcc.citric
package controller

import controller.states.startAndEnd.PreGame
import states._
import model.unit.player.IPlayer

import collection.mutable.Queue

/** Represent The Game.
 *
 *  Each game match is divided into chapters, which are further divided into
 *  4 turns, one for each player.
 *
 *  At the beginning of the match, the turn order is randomly assigned to the
 *  players and remains the same throughout the entire match.
 *
 *  Whenever all 4 players finish their respective turns, the
 *  game proceeds to the next chapter, which in this case serves as a mere counter.
 *
 *  Each player's turn is structured as follows:
 *   - If they are in a K.O. state, they move to the Recovery phase.
 *   - Receive stars.
 *   - Roll a die and move the number of panels indicated by the result, except in cases where they can stop prematurely.
 *   - The panel's effect is activated.
 *   - Proceed to the next player's turn.
 *
 *  @author [[https://github.com/frgonzal/ Franco González L.]]
 */
class GameController extends GameTransitions with GameChecks {

    private var _characters: Queue[IPlayer] = Queue()

    /** Returns the Player that has to play its turn. */
    def character(): IPlayer = _characters.front

    /** Rotates the List of characters, useful to change Player Turns. */
    def rotateCharacters(): Unit = _characters.enqueue(_characters.dequeue())

    /** Initial Game State */
    var state: GameState = new PreGame(this)

    /** This function should be called every time the Game State changes.
     *
     * @param newState This is the new State of the Game.
     */
    def setState(newState: GameState): Unit = {
        state = newState
    }

    /** Depends on the state.*/
    def play(): Unit = state.play()

    def startGame(): Unit = state.startGame()

    def finishGame(): Unit = state.finishGame()

    def nextChapter(): Unit = state.nextChapter()

    def recoverPlayer(): Unit = state.recoverPlayer()

    def requirementsAchieved(): Unit = state.requirementsAchieved()

    def requirementsNotAchieved(): Unit = state.requirementsNotAchieved()

    def playTurn(): Unit = state.playTurn()

    def rollDice(): Unit = state.rollDice()

    def choosePath(): Unit = state.choosePath()

    def stop(): Unit = state.stop()

    def noMovementsLeft(): Unit = state.noMovementsLeft()

    def pass(): Unit = state.pass()

    def choosePlayer(): Unit = state.choosePlayer()

    def finishCombat(): Unit = state.finishCombat()

    def encounterPanelEffect(): Unit = state.encounterPanelEffect()

    def nextTurn(): Unit = state.nextTurn()

    def playAgain(): Unit = state.playAgain()

    def isStarting: Boolean = state.isStarting

    def hasFinished: Boolean = state.hasFinished
}
