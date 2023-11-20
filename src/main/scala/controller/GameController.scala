package cl.uchile.dcc.citric
package controller

import cl.uchile.dcc.citric.controller.states.startAndEnd.PreGame
import states._

class GameController {
    /* Initial Game State */
    var state: GameState = new PreGame(this)

    def setState(newState: GameState): Unit = {
        state = newState
    }

    def startGame(): Unit = state.startGame()
    def finishGame(): Unit = state.finishGame()
    def nextChapter(): Unit = state.nextChapter()
    def recoverPlayer(): Unit = state.recoverPlayer()
    def requirementsAchieved(): Unit = state.requirementsAchieved()
    def requirementsNotAchieved(): Unit = state.requirementsNotAchieved()
    def play(): Unit = state.play()
    def rollDice(): Unit = state.rollDice()
    def choosePath(): Unit = state.choosePath()
    def stop(): Unit = state.stop()
    def noMovementsLeft(): Unit = state.noMovementsLeft()
    def pass(): Unit = state.pass()
    def choosePlayer(): Unit = state.choosePlayer()
    def attack(): Unit = state.attack()
    def defend(): Unit = state.defend()
    def evade(): Unit = state.evade()
    def deliverReward(): Unit = state.deliverReward()
    def encounterPanelEffect(): Unit = state.encounterPanelEffect()
    def nextTurn(): Unit = state.nextTurn()
    def playAgain(): Unit = state.playAgain()

    def isStarting(): Boolean = state.isStarting()
    def hasFinished(): Boolean = state.hasFinished()
}
