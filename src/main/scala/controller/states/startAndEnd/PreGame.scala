package cl.uchile.dcc.citric
package controller.states.startAndEnd

import controller.GameController
import controller.states.{Chapter, GameState}
import model.unit.player.IPlayer

import cl.uchile.dcc.citric.factory.panel.{BasicMapFactory, MapFactory}
import cl.uchile.dcc.citric.factory.unit.PlayerFactory
import cl.uchile.dcc.citric.view.msg.concrete.Msg.StringMsg

/** Controls the Pre Game settings.
 *
 *  Load game resources, initialize game settings, and prepare the game environment.
 */
class PreGame(controller: GameController) extends GameState(controller) {


    override def startGame(): Unit = {
        val players: Array[IPlayer] = setPlayers()
        mapFactory.setPlayers(players)
        mapFactory.createMap()
        this.changeState(new Chapter(controller))
    }

    private def setPlayers(): Array[IPlayer] = {
        var players: Array[IPlayer] = Array()
        for(playerIndex <- 1 to controller.numberOfPlayers){
            val name: String =
                controller.view.receiveStringInput(new StringMsg(s"Choose Name for player $playerIndex: "))


            playerFactory.setRandomStats()
            val player: IPlayer = playerFactory.createUnit(name)

            controller.view.sendMsg(new StringMsg(s"A new Player has been created:\n ${player.toString}"))

            /* Register the controller as an observer of the player. */
            player.registerObserver(controller)
            player.notifyObservers(player)

            /* Add the character to the Game. */
            controller.addCharacter(player)

            players = players :+ player
        }
        players
    }

    override def isStarting: Boolean = true

    private var mapFactory: MapFactory = new BasicMapFactory
    private val playerFactory: PlayerFactory = new PlayerFactory()
}
