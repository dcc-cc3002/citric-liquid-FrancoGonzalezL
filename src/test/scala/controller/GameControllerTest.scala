package cl.uchile.dcc.citric
package controller

import view.NullView

import factory.unit.{PlayerFactory, UnitFactory}
import model.panel.Panel
import model.panel.concretePanel.HomePanel
import model.unit.player.IPlayer

class GameControllerTest extends munit.FunSuite {
    val playerFactory: UnitFactory[IPlayer] = new PlayerFactory
    var player: IPlayer = _
    var game: GameController = _
    val starsGoal: String = "Stars"

    override def beforeEach(context: BeforeEach): Unit = {
        playerFactory.setRandomStats()
        player = playerFactory.createUnit("Test Player")
        game = new GameController
    }


}

