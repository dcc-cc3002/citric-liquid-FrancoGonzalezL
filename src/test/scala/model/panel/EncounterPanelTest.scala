package cl.uchile.dcc.citric
package model.panel

import model.unit.IUnit
import model.unit.player.{IPlayer, PlayerCharacter}

import cl.uchile.dcc.citric.model.panel.encounter.EncounterPanel

class EncounterPanelTest extends munit.FunSuite {
    var encounterPanel: EncounterPanel = _

    var player1: IPlayer = _
    val name1: String = "player1"
    val maxHp: Int = 100
    val attack: Int = 10
    val defense: Int = 10
    val evasion: Int = 10

    override def beforeEach(context: BeforeEach): Unit = {
        player1 = new PlayerCharacter(name1, maxHp, attack, defense, evasion)
        encounterPanel = new EncounterPanel
    }

    test("The effect of panel will generate a random Unit"){
        assert(encounterPanel.wildUnit.isEmpty)
        encounterPanel.apply(player1)
        assert(encounterPanel.wildUnit.isDefined)
    }

    test("The wildUnits will remain on the panel until they are defeated"){
        encounterPanel.apply(player1)
        val first_wildUnit: IUnit = encounterPanel.wildUnit.get
        encounterPanel.wildUnit.get.hp -= 10
        encounterPanel.apply(player1)
        assertNotEquals(first_wildUnit, encounterPanel.wildUnit.get)
    }
}
