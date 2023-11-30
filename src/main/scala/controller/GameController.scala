package cl.uchile.dcc.citric
package controller

import controller.states.startAndEnd.PreGame
import states._
import states.startAndEnd._
import model.unit.player.IPlayer
import model.panel.Panel
import observer.{ISubject, Observer}
import exceptions.InvalidInputException
import scala.io.StdIn
import scala.collection.mutable
import scala.util.Random

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
class GameController extends GameTransitions with GameChecks with Observer[IPlayer] {

    /** Initial Game State */
    private var state: GameState = new PreGame(this)

    private val _characters: mutable.Queue[IPlayer] = mutable.Queue()
    private var _chapter: Int = 0
    private var _turn: Int = 0
    private var _panels: Map[IPlayer, Panel] = Map()
    private var debug: Boolean = false

    /** This function should be called every time the Game State changes.
     *
     * @param newState This is the new State of the Game.
     */
    protected[controller] def setState(newState: GameState): Unit = {
        state = newState
    }

    def turn: Int = _turn

    def chapter: Int = _chapter

    /** Returns the Player that has to play its turn. */
    protected[controller] def currentCharacter: IPlayer = _characters.front

    protected[controller] def currentPanel: Panel = {
        val panel: Option[Panel] = _panels.get(this.currentCharacter)
        if (panel.isDefined) panel.get
        else throw new AssertionError("Player is Not associated with a Panel")
    }

    protected[controller] def advanceChapter(): Unit = {
        val msgChapter: String = s"Chapter ${_chapter}"
        sendMsg(msgChapter)
        _chapter += 1
    }

    protected[controller] def moveCharacterToPanel(panel: Panel): Unit = {
        if (!currentPanel.containsNextPanel(panel))
            throw new AssertionError("Player is not allowed to move to this Panel")

        currentPanel.removeCharacter(currentCharacter)
        panel.addCharacter(currentCharacter)
        _panels = _panels + (currentCharacter -> panel)
    }

    /** Rotates the List of characters, useful to change Player Turns. */
    protected[controller] def rotateCharacters(): Unit = {
        _characters.enqueue(_characters.dequeue())
        _turn = (this.turn+1) % 4
    }


    /** Depends on the state.*/
    protected[controller] def play(): Unit = state.play()

    def startGame(debug: Boolean = false): Unit = state.startGame(debug)

    def finishGame(): Unit = state.finishGame()

    def recoverPlayer(): Unit = state.recoverPlayer()

    def playTurn(): Unit = state.playTurn()

    def stop(): Unit = state.stop()

    def finishCombat(): Unit = state.finishCombat()

    def encounterPanel(): Unit = state.encounterPanel()

    def nextTurn(): Unit = state.nextTurn()

    def playAgain(): Unit = state.playAgain()

    def isStarting: Boolean = state.isStarting

    def hasFinished: Boolean = state.hasFinished


    /* Winner of the Game */
    override def update(subject: ISubject[IPlayer], msg: IPlayer): Unit = {
        val winner: IPlayer = msg
        if (winner.normaLvl < 6) throw new AssertionError("The player should have Norma 6 to win the Game")

        this.setState(new EndGame(this, winner))
    }

    protected[controller] def sendMsg(msg: String): Unit = {
        if(!debug) println(msg)
    }

    protected[controller] def receiveInput(msg: String, numberOfOptions: Int): Int = {
        sendMsg(msg)
        var selected: Int = 1

        if(!debug) selected = StdIn.readInt()
        else       selected = new Random().nextInt(numberOfOptions) + 1

        if( selected < 1 || selected > numberOfOptions)
            throw new InvalidInputException(s"Input should be greater than 0 and less than $numberOfOptions")

        selected
    }

    def run(debug: Boolean = false): Unit = {
        if(!isStarting) throw new AssertionError("The Game has not started.")

        while(!hasFinished)
            play()
        play()

        val msg: String = "Play Again?"
        val numberOfOptions: Int = 2
        val selection: Int = receiveInput(msg, numberOfOptions)

        if(selection == 1) run()
        else sendMsg("Good Bye")
    }

    protected[controller] def debug_=(newDebug: Boolean): Unit = {
        debug = newDebug
    }
}