package cl.uchile.dcc.citric
package model.panel

import model.unit.player.{IPlayer, PlayerCharacter}
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

    test("Every Panel has a type"){
        val neutral2: Panel = new NeutralPanel
        assertEquals(neutralPanel.panelType, neutral2.panelType)
        assertNotEquals(neutralPanel.panelType, bonusPanel.panelType)
        assertNotEquals(neutralPanel.panelType, dropPanel.panelType)
        assertNotEquals(neutralPanel.panelType, homePanel.panelType)
    }

    test("A Panel could be occupied by one or more players"){
        val player2: IPlayer = new PlayerCharacter("player2", maxHp, attack, defense, evasion)

        neutralPanel.addCharacter(player1)
        assertEquals(neutralPanel.characters.size,1)
        neutralPanel.addCharacter(player2)
        assertEquals(neutralPanel.characters.size,2)
    }

    test("A Panel has one or more nextPanels"){
        assert(neutralPanel.nextPanels.nonEmpty)

        neutralPanel.addNextPanel(bonusPanel, multi_link_mode = false)
        assertEquals(neutralPanel.nextPanels.size, 1)

        neutralPanel.addNextPanel(homePanel, multi_link_mode = true)
        assertEquals(neutralPanel.nextPanels.size, 2)
    }

    test("otherPanel Can't be added two times to the same Panel") {
        neutralPanel.addNextPanel(bonusPanel,multi_link_mode = false)
        neutralPanel.addNextPanel(bonusPanel,multi_link_mode = false)
        assertEquals(neutralPanel.nextPanels.size, 1)
    }

    test("only the single Panels can be linked to itself"){
        assertNotEquals(neutralPanel.nextPanels.indexOf(neutralPanel),-1)
        neutralPanel.addNextPanel(bonusPanel, multi_link_mode = true)
        assertEquals(neutralPanel.nextPanels.indexOf(neutralPanel), -1)
    }

    test("A character can't be added nor removed twice"){
        neutralPanel.addCharacter(player1)
        neutralPanel.addCharacter(player1)
        assertEquals(neutralPanel.characters.size,1)

        neutralPanel.removeCharacter(player1)
        neutralPanel.removeCharacter(player1)
        assertEquals(neutralPanel.characters.size,0)
    }

    test("A Panel can not be linked to itself"){
        neutralPanel.addNextPanel(bonusPanel, multi_link_mode = false)
        assertEquals(neutralPanel.nextPanels.indexOf(neutralPanel),-1)
        neutralPanel.addNextPanel(bonusPanel, multi_link_mode = false)
        assertEquals(neutralPanel.nextPanels.indexOf(neutralPanel),-1)
    }
}
