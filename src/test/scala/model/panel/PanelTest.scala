package cl.uchile.dcc.citric
package model.panel

import model.unit.player.{IPlayer, PlayerCharacter}

import cl.uchile.dcc.citric.model.panel.concretePanel.{BonusPanel, DropPanel, HomePanel, NeutralPanel}

import scala.util.Random

class PanelTest extends munit.FunSuite {
    var neutralPanel: Panel = _
    var bonusPanel: Panel = _
    var dropPanel: Panel = _
    var homePanel: Panel = _

    var player1: IPlayer = _
    val name1: String = "player1"
    val maxHp: Int = 100
    val attack: Int = 10
    val defense: Int = 10
    val evasion: Int = 10

    override def beforeEach(context: BeforeEach): Unit = {
        player1 = new PlayerCharacter(name1, maxHp, attack, defense, evasion)

        neutralPanel = new NeutralPanel
        bonusPanel = new BonusPanel
        dropPanel = new DropPanel
        homePanel = new HomePanel(player1)
    }

    test("A player is correctly added to the Panel"){
        assert(neutralPanel.addCharacter(player1))
        assert(neutralPanel.containsCharacter(player1))
    }
    test("A player should be added to be contained in the Panel"){
        assert(!neutralPanel.containsCharacter(player1))
    }

    test("A Panel could be occupied by one or more players"){
        val player2: IPlayer = new PlayerCharacter("player2", maxHp, attack, defense, evasion)
        neutralPanel.addCharacter(player1)
        assertEquals(neutralPanel.charactersCount,1)
        neutralPanel.addCharacter(player2)
        assertEquals(neutralPanel.charactersCount,2)
    }

    test("A character can't be added nor removed twice") {
        assert(neutralPanel.addCharacter(player1))
        assert(!neutralPanel.addCharacter(player1))
        assert(neutralPanel.removeCharacter(player1))
        assert(!neutralPanel.removeCharacter(player1))
    }

    test("A Panel might have more than 1 nextPanel") {
        neutralPanel.addNextPanel(bonusPanel)
        neutralPanel.addNextPanel(dropPanel)
        assertEquals(neutralPanel.nextPanelsCount, 2)
    }

    test("otherPanel Can't be added two times to the same Panel") {
        neutralPanel.addNextPanel(bonusPanel)
        neutralPanel.addNextPanel(bonusPanel)
        assertEquals(neutralPanel.nextPanelsCount, 1)
    }

    test("A Panel should not be linked to itself"){
        assert(!neutralPanel.containsNextPanel(neutralPanel))
        assert(!neutralPanel.addNextPanel(neutralPanel))
    }

    test("otherPanel should not be added nor removed twice"){
        assert(neutralPanel.addNextPanel(dropPanel))
        assert(!neutralPanel.addNextPanel(dropPanel))

        assert(neutralPanel.addNextPanel(homePanel))
        assert(neutralPanel.removeNextPanel(homePanel))
        assert(!neutralPanel.removeNextPanel(homePanel))
    }
}
