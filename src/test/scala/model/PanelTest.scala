package cl.uchile.dcc.citric
package model

import scala.util.Random

class PanelTest extends munit.FunSuite {
    var panel_test: Panel = _

    override def beforeEach(context: BeforeEach): Unit = {
        panel_test = new NeutralPanel
    }

    test("Every Panel has a type"){
        val panel1: Panel = new NeutralPanel
        val panel2: Panel = new BonusPanel

        assertEquals(panel_test.panelType, panel1.panelType)
        assertNotEquals(panel_test.panelType, panel2.panelType)
    }
    test("A Panel could be occupied by one or more players"){
        val player1: PlayerCharacter = new PlayerCharacter(
            "player1",10,2,1,1, new Random(2)
        )
        val player2: PlayerCharacter = new PlayerCharacter(
            "player2", 10, 2, 1, 1, new Random(3)
        )
        panel_test.addCharacter(player1)
        assertEquals(panel_test.characters.size,1)
        panel_test.addCharacter(player2)
        assertEquals(panel_test.characters.size,2)
    }
    test("A Panel has one or more nextPanels"){
        assert(panel_test.nextPanels.nonEmpty)

        val panel1: Panel = new DropPanel
        val panel2: Panel = new BonusPanel

        panel_test.addNextPanel(panel1)
        assertEquals(panel_test.nextPanels.size, 1)

        panel_test.addNextPanel(panel2, multi_link_mode = true)
        assertEquals(panel_test.nextPanels.size, 2)
    }
    test("otherPanel Can't add two times to the same Panel") {
        val otherPanel: Panel = new NeutralPanel
        panel_test.addNextPanel(otherPanel)
        panel_test.addNextPanel(otherPanel)
        assertEquals(panel_test.nextPanels.size, 1)
    }
    test("only the single Panels can be linked to itself"){
        val panel1: Panel = new NeutralPanel
        panel_test.addNextPanel(panel1, multi_link_mode = true)
        assertEquals(panel_test.nextPanels.indexOf(panel_test), -1)
    }
    test("A character can't be added nor removed twice"){
        val player1: PlayerCharacter = new PlayerCharacter(
            "player1", 10, 2, 1, 1, new Random(1)
        )

        panel_test.addCharacter(player1)
        panel_test.addCharacter(player1)
        assertEquals(panel_test.characters.size,1)

        panel_test.removeCharacter(player1)
        panel_test.removeCharacter(player1)
        assertEquals(panel_test.characters.size,0)
    }
    test("A Panel can not be linked to itself"){
        val otherPanel: Panel = new BonusPanel
        panel_test.addNextPanel(otherPanel)
        assertEquals(panel_test.nextPanels.indexOf(panel_test),-1)
        panel_test.addNextPanel(otherPanel)
        assertEquals(panel_test.nextPanels.indexOf(panel_test),-1)
    }
}
