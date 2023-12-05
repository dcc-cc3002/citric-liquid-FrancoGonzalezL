package cl.uchile.dcc.citric
package model.panel

import model.unit.IUnit
import model.unit.player.{IPlayer, PlayerCharacter}

import cl.uchile.dcc.citric.model.panel.concretePanel.EncounterPanel
import cl.uchile.dcc.citric.model.unit.wildUnit.concreteWildUnit.{Chicken, RoboBall, Seagull}

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

    test("The effect of panel will generate a random Unit that will remain until they are defeated. "){
        encounterPanel.apply(player1)
        val className: String = encounterPanel.wildUnit.getClass.toString
        assert(Array(classOf[Chicken].toString, classOf[Seagull].toString, classOf[RoboBall].toString).contains(className))
    }

    test("The wildUnits will remain on the panel until they are defeated"){
        encounterPanel.apply(player1)
        assert(encounterPanel.wildUnit.hp > 0)
        val unit: IUnit = encounterPanel.wildUnit

        encounterPanel.wildUnit.hp = 0

        encounterPanel.apply(player1)
        assertNotEquals(unit, encounterPanel.wildUnit)
    }
}
