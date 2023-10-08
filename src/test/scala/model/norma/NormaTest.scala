package cl.uchile.dcc.citric
package model.norma

import model.unit.player.{IPlayer, PlayerCharacter}
import model.panel.{Panel, HomePanel, NeutralPanel}

class NormaTest extends munit.FunSuite {
    val StarsGoal: String = "Stars"
    val VictoriesGoal: String = "Victories"
    val name1: String = "player1"
    val maxHp: Int = 100
    val attack: Int = 10
    val defense: Int = 10
    val evasion: Int = 10

    var homePanel: Panel = _
    var player: IPlayer = _

    override def beforeEach(context: BeforeEach): Unit = {
        player = new PlayerCharacter(name1, maxHp, attack, defense, evasion)
        homePanel = new HomePanel(player)
    }

    test("All players start the game with Norma 1"){
        assertEquals(player.norma, expected=1)
    }

    test("The goal can not be changed until it is completed"){
        //chose the goal
        assert(player.goal = StarsGoal)
        //can not be changed
        assert(!(player.goal = VictoriesGoal))
        //complete the goal
        player.stars += 10
        homePanel.apply(player)
        //try again
        assert(player.goal = VictoriesGoal)
    }

    test("The goal should be either 'Stars' or 'Victories'"){
        assert(!(player.goal = "Hp"))
        assert(!(player.goal = "stars"))
        assert(!(player.goal = "victories"))
        assert(player.goal = VictoriesGoal)
    }

    test("The players should have a goal to level up their Norma"){
        assert(!homePanel.apply(player))
        player.goal = StarsGoal
        player.stars += 10
        assert(homePanel.apply(player))
    }

    test("The level of the Norma should not be greater than 6"){
        player.stars = 500
        for(_ <- 2 to 6){
            player.goal = StarsGoal
            homePanel.apply(player)
        }
        assertEquals(player.norma, 6)
        player.goal = StarsGoal
        assert(!homePanel.apply(player))
    }

    test("The player should only level up if the player has achieved the requirements of the current goal"){
        player.goal = StarsGoal
        player.stars += 9
        assert(!homePanel.apply(player))
    }

    test("The Norma Check should be done in a HomePanel"){
        val neutralPanel: Panel = new NeutralPanel
        player.goal = StarsGoal
        player.stars = 100
        assert(!player.normaCheck(neutralPanel))
        assert(player.normaCheck(homePanel))
    }
}
