package cl.uchile.dcc.citric
package controller

import view.NullView

import cl.uchile.dcc.citric.factory.unit.{PlayerFactory, UnitFactory}
import cl.uchile.dcc.citric.model.panel.Panel
import cl.uchile.dcc.citric.model.panel.concretePanel.HomePanel
import cl.uchile.dcc.citric.model.unit.player.IPlayer

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

    test("When a player has achieved Norma lvl 6 the winner should be defined. ") {
        game.setView(new NullView)

        /* Register the controller */
        player.registerObserver(game)
        /* new Home Panel for the Norma Check. */
        val homePanel: Panel = new HomePanel(player)
        /* Player should be on the Panel. */
        homePanel.addCharacter(player)

        /* A lot of stars. */
        player.stars += 1000000

        /* There is no winner initially. */
        assert(game.winner.isEmpty)

        /* from norma 1 to norma 6 */
        for(_ <- 2 to 6){
            assert(player.goal = starsGoal)
            homePanel.apply(player)
        }

        /* Now winner is defined. */
        assertEquals(player.normaLvl, 6)
        assert(game.winner.isDefined)
        /* And the winner is the player with Norma lvl 6. */
        assertEquals(player, game.winner.get)
    }
}

