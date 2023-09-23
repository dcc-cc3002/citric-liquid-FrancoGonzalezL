package cl.uchile.dcc.citric
package model.panel

import model.unit.player.{IPlayer, PlayerCharacter}

class HomePanelTest extends munit.FunSuite {
    var homePanel: HomePanel = _

    var player1: IPlayer = _
    val name1: String = "player1"
    val maxHp: Int = 100
    val attack: Int = 10
    val defense: Int = 10
    val evasion: Int = 10

    override def beforeEach(context: BeforeEach): Unit = {
        player1 = new PlayerCharacter(name1, maxHp, attack, defense, evasion)
        homePanel = new HomePanel(player1)
    }

    test("A Home Panel only has 1 owner") {
        val homePanel2: HomePanel = new HomePanel(player1)
        assertEquals(homePanel2.owner, player1)
        val player2: IPlayer = new PlayerCharacter("player2", maxHp, attack, defense, evasion)
        assertNotEquals(homePanel2.owner, player2)
    }

    test("A Home Panel increases the current amount of hp in 1 point") {
        player1.reduceHp(30)
        val hp: Int = player1.getHp
        homePanel.effect(player1)
        assertEquals(player1.getHp, hp + 1)
    }

    test("A Home Panel performs a Norma Check") {
        val lvlNorma: Int = player1.getNorma
        player1.setGoal("Stars")
        player1.addStars(10)
        homePanel.effect(player1)
        assertEquals(player1.getNorma, lvlNorma + 1)
    }
}
