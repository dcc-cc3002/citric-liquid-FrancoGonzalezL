package cl.uchile.dcc.citric
package controller

import controller.states.startAndEnd.PreGame

import states._
import model.unit.player.IPlayer
import model.panel.Panel
import observer.{ISubject, Observer}
import view.msg.{PlayAgainMsg, StringMsg}
import view.{IView, View}

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

    /* To Simulate the entire Game. */
    def run(): Unit = {
        if (!isStarting) throw new AssertionError("The Game has not started.")

        while (!hasFinished)
            play()
        play()

        val selection: Int = view.receiveIntInput(new PlayAgainMsg)

        if (selection == 1) run()
        else view.sendMsg(new StringMsg("Good Bye"))
    }

    /* To Simulate a step in the game. */
    private def play(): Unit = state.play()

    protected[controller] def advanceTurn(): Unit = {
        _turn = (_turn + 1)%numberOfPlayers
    }

    /* When a player has achieved Norma lvl 6, the controller should be notified. */
    override def update(subject: ISubject[IPlayer], msg: IPlayer): Unit = {
        val winner: IPlayer = msg
        if (winner.normaLvl < 6) throw new AssertionError("The player should have Norma 6 to win the Game")
        _winner = Some(winner)
    }

    /* This should change the View. Useful for debugging. */
    def setView(view: IView): Unit = {
        _view = view
    }

    /* Getter for the variable Turn. */
    def turn: Int = _turn + 1

    /* Getter for the variable Chapter. */
    def chapter: Int = _chapter

    /* Getter for the winner of the game. */
    protected[controller] def winner: Option[IPlayer] = _winner

    /** This function should be called every time the Game State changes.
     *
     * @param newState This is the new State of the Game.
     */
    protected[controller] def setState(newState: GameState): Unit = {
        state = newState
    }

    /* Getter for the view of the Game. */
    protected[controller] def view: IView = _view


    /** Returns the Player that has to play its turn. */
    protected[controller] def currentCharacter: IPlayer = _characters(turn)

    protected[controller] def addCharacter(player: IPlayer): Unit = {
        if(_characters.length < numberOfPlayers)
            _characters = _characters :+ player
        else
            throw new AssertionError("No more players can be added to the Game.")
    }

    protected[controller] def currentPanel: Panel = {
        val panel: Option[Panel] = _playerPositions.get(this.currentCharacter)
        if (panel.isDefined) panel.get
        else throw new AssertionError("Player is Not associated with a Panel")
    }

    protected[controller] def advanceChapter(): Unit = {
        view.sendMsg(new StringMsg(s"Chapter ${this.chapter}"))
        _chapter += 1
    }

    protected[controller] def moveCharacterToPanel(panel: Panel): Unit = {
        if (!currentPanel.containsNextPanel(panel))
            throw new AssertionError("Player is not allowed to move to this Panel")

        currentPanel.removeCharacter(currentCharacter)
        panel.addCharacter(currentCharacter)
        _playerPositions = _playerPositions + (currentCharacter -> panel)
    }

    def startGame(): Unit = state.startGame()
    def finishGame(): Unit = state.finishGame()
    def recoverPlayer(): Unit = state.recoverPlayer()
    def playTurn(): Unit = state.playTurn()
    def stop(): Unit = state.stop()
    def finishCombat(): Unit = state.finishCombat()
    def encounter(): Unit = state.encounter()
    def nextTurn(): Unit = state.nextTurn()
    def playAgain(): Unit = state.playAgain()
    def isStarting: Boolean = state.isStarting
    def hasFinished: Boolean = state.hasFinished


    /** Initial Game State */
    private var state: GameState = new PreGame(this)
    /* An Array with all the characters, useful for the turns. */
    private var _characters: Array[IPlayer] = Array()
    /* The current number of the Chapter */
    private var _chapter: Int = 0
    /* The turn of a player. 0 <= turn <= 3. it is initially -1 because new Chapter always adds 1. */
    private var _turn: Int = -1
    /* The positions of all the players are mapped on this var. */
    private var _playerPositions: Map[IPlayer, Panel] = Map()
    /* The winner of the game. */
    private var _winner: Option[IPlayer] = None
    /* The view that the controller is going to use. Can be changed. */
    private var _view: IView = new View()
    /* The number of Players in a Game. */
    protected[controller] val numberOfPlayers: Int = 4
}