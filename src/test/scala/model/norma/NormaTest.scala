package cl.uchile.dcc.citric
package model.norma

import model.unit.player.{IPlayer, PlayerCharacter}
import model.panel.Panel
import cl.uchile.dcc.citric.model.panel.concretePanel.{HomePanel, NeutralPanel}

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
        homePanel.addCharacter(player)
    }

    test("All players start the game with Norma 1"){
        assertEquals(player.normaLvl, expected=1)
    }

    test("The goal can not be changed until it is completed"){
        //choose the goal
        assert(player.goal = StarsGoal)
        assert(player.goal == StarsGoal)
        //can not be changed
        assert(!(player.goal = VictoriesGoal))
        assert(player.goal != VictoriesGoal)
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
        homePanel.addCharacter(player)

        homePanel.apply(player)
        assertEquals(player.normaLvl, 1)
        player.goal = StarsGoal
        player.stars += 10

        homePanel.apply(player)
        assertEquals(player.normaLvl, 2)

    }

    test("The level of the Norma should not be greater than 6"){
        player.stars = 500
        for(lvl <- 1 to 5){
            assertEquals(player.normaLvl, lvl)
            player.goal = StarsGoal
            homePanel.apply(player)
        }
        assertEquals(player.normaLvl, 6)
        player.goal = StarsGoal
        homePanel.apply(player)
        assertEquals(player.normaLvl, expected=6)
    }

    test("The player should only level up if the player has achieved the requirements of the current goal"){
        val prevNormaLvl: Int = player.normaLvl
        player.goal = StarsGoal
        player.stars += 9
        homePanel.apply(player)
        assertEquals(player.normaLvl, prevNormaLvl)
    }
}
