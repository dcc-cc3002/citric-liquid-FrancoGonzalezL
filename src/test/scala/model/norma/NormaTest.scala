package cl.uchile.dcc.citric
package model.norma

import model.unit.player.{IPlayer, PlayerCharacter}
import model.panel.Panel
import cl.uchile.dcc.citric.model.panel.concretePanel.{HomePanel, NeutralPanel}

class NormaTest extends munit.FunSuite {
    val starsGoalInput: Int = 1
    val victoriesGoalInput: Int = 2

    val starsGoalString: String = "Stars"
    val victoriesGoalString: String = "Victories"

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
        assert(player.goal = starsGoalInput)
        assert(player.goal == starsGoalString)
        //can not be changed
        assert(!(player.goal = victoriesGoalInput))
        assert(player.goal != victoriesGoalString)
        //complete the goal
        player.stars += 10
        homePanel.apply(player)
        //try again
        assert(player.goal = victoriesGoalInput)
    }

    test("The goal should be either 'Stars' or 'Victories'"){
        assert(!(player.goal = 0))
        assert(!(player.goal = 10))
        assert(player.goal = victoriesGoalInput)
    }

    test("The players should have a goal to level up their Norma"){
        homePanel.addCharacter(player)

        homePanel.apply(player)
        assertEquals(player.normaLvl, 1)
        player.goal = starsGoalInput
        player.stars += 10

        homePanel.apply(player)
        assertEquals(player.normaLvl, 2)

    }

    test("The level of the Norma should not be greater than 6"){
        player.stars = 500
        for(lvl <- 1 to 5){
            assertEquals(player.normaLvl, lvl)
            player.goal = starsGoalInput
            homePanel.apply(player)
        }
        assertEquals(player.normaLvl, 6)
        player.goal = starsGoalInput
        homePanel.apply(player)
        assertEquals(player.normaLvl, expected=6)
    }

    test("The player should only level up if the player has achieved the requirements of the current goal"){
        val prevNormaLvl: Int = player.normaLvl
        player.goal = starsGoalInput
        player.stars += 9
        homePanel.apply(player)
        assertEquals(player.normaLvl, prevNormaLvl)
    }
}
