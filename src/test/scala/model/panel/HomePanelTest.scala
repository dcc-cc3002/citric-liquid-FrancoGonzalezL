package cl.uchile.dcc.citric
package model.panel

import model.unit.player.{IPlayer, PlayerCharacter}
import model.panel.concretePanel.{BonusPanel, HomePanel}
import model.unit.IUnit
import factory.unit.{PlayerFactory, UnitFactory}

class HomePanelTest extends munit.FunSuite {
    val playerFactory: UnitFactory[IPlayer] = new PlayerFactory

    var homePanel: HomePanel = _
    var player: IPlayer = _
    val name: String = "player1"

    override def beforeEach(context: BeforeEach): Unit = {
        playerFactory.setRandomStats()
        player = playerFactory.createUnit(name)

        homePanel = new HomePanel(player)
        homePanel.addCharacter(player)
    }

    test("A Home Panel only has 1 owner") {
        val homePanel2: HomePanel = new HomePanel(player)
        assertEquals(homePanel2.owner, player)

        playerFactory.setRandomStats()
        val player2: IPlayer = playerFactory.createUnit("C")
        assertNotEquals(homePanel2.owner, player2)
    }

    test("A Home Panel increases the current amount of hp in 1 point") {
        player.hp -= 30
        val hp: Int = player.hp
        homePanel.apply(player)
        assertEquals(player.hp, hp + 1)
    }

    test("If a player is the owner of a HomePanel, the player can stop even if the player has moves left"){
        val player2: IPlayer = playerFactory.createUnit("A")
        val panel: Panel = new BonusPanel

        assert(homePanel.canStopHere(player))
        assert(!homePanel.canStopHere(player2))
        assert(!panel.canStopHere(player))
        assert(!panel.canStopHere(player2))
    }

    test("A Home Panel performs a Norma Check") {
        val lvlNorma: Int = player.normaLvl
        player.goal = "Stars"
        player.stars += 10
        homePanel.apply(player)
        assertEquals(player.normaLvl, lvlNorma + 1)
    }
}
