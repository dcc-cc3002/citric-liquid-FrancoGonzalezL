package cl.uchile.dcc.citric
package model.panel

import model.unit.player.{IPlayer, PlayerCharacter}

import cl.uchile.dcc.citric.model.panel.concretePanel.DropPanel

class DropPanelTest extends munit.FunSuite {
    var dropPanel: Panel = _

    var player1: IPlayer = _
    val name1: String = "player1"
    val maxHp: Int = 100
    val attack: Int = 10
    val defense: Int = 10
    val evasion: Int = 10

    override def beforeEach(context: BeforeEach): Unit = {
        player1 = new PlayerCharacter(name1, maxHp, attack, defense, evasion)
        dropPanel = new DropPanel
    }

    test("A Drop Panel removes 0 or more stars") {
        player1.stars = 100
        val stars: Int = player1.stars
        dropPanel.apply(player1)
        assert(player1.stars <= stars)
    }
}
