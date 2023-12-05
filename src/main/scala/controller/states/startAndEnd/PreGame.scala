package cl.uchile.dcc.citric
package controller.states.startAndEnd

import controller.GameController
import controller.states.{Chapter, GameState}
import model.unit.player.IPlayer
import view.msg.StringMsg
import cl.uchile.dcc.citric.factory.unit.PlayerFactory

/** Controls the Pre Game settings,
 *  should initialize all the variables to play the game.
 */
class PreGame(controller: GameController) extends GameState(controller) {
    private val playerFactory: PlayerFactory = new PlayerFactory()

    override def startGame(): Unit = {
        setPlayers()

        this.changeState(new Chapter(controller))
    }

    override def isStarting: Boolean = true

    private def setPlayers(): Unit = {
        for(_ <- 1 to controller.numberOfPlayers){
            val name: String =
                controller.view.receiveStringInput(new StringMsg("Choose Name for player $i "))
            playerFactory.setRandomStats()
            val player: IPlayer = playerFactory.createUnit(name)

            /* Register the controller as an observer of the player. */
            player.registerObserver(controller)

            /* Add the character to the Game. */
            controller.addCharacter(player)
        }
    }
}
