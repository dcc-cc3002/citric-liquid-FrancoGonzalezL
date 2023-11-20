package cl.uchile.dcc.citric
package controller.states

import controller.GameController

class GameState(controller: GameController) {
    protected def changeState(state: GameState): Unit = {
        controller.setState(state)
    }

    private def error() = throw new AssertionError("Wrong State")

    def startGame(): Unit = error()
    def finishGame(): Unit = error()
    def nextChapter(): Unit = error()
    def recoverPlayer(): Unit = error()
    def requirementsAchieved(): Unit = error()
    def requirementsNotAchieved(): Unit = error()
    def play(): Unit = error()
    def rollDice(): Unit = error()
    def choosePath(): Unit = error()
    def stop(): Unit = error()
    def noMovementsLeft(): Unit = error()
    def pass(): Unit = error()
    def choosePlayer(): Unit = error()
    def attack(): Unit = error()
    def defend(): Unit = error()
    def evade(): Unit = error()
    def deliverReward(): Unit = error()
    def encounterPanelEffect(): Unit = error()
    def nextTurn(): Unit = error()
    def playAgain(): Unit = error()


    def isStarting(): Boolean = false
    def hasFinished(): Boolean = false
}
