package cl.uchile.dcc.citric
package model.panel

import model.unit.player.{IPlayer, PlayerCharacter}

import cl.uchile.dcc.citric.model.panel.concretePanel.{BonusPanel, NeutralPanel}

class BonusPanelTest extends munit.FunSuite {
    var bonusPanel: Panel = _

    var player1: IPlayer = _
    val name1: String = "player1"
    val maxHp: Int = 100
    val attack: Int = 10
    val defense: Int = 10
    val evasion: Int = 10

    override def beforeEach(context: BeforeEach): Unit = {
        player1 = new PlayerCharacter(name1, maxHp, attack, defense, evasion)
        bonusPanel = new BonusPanel
    }

    test("A Bonus Panel adds 1 or more stars") {
        val neutralPanel: Panel = new NeutralPanel
        val stars: Int = player1.stars

        neutralPanel.apply(player1)
        assert(player1.stars == stars)

        bonusPanel.apply(player1)
        assert(player1.stars > stars)
    }
}
