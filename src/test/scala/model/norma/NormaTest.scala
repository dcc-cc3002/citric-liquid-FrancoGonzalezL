package cl.uchile.dcc.citric
package model.norma

import model.unit.player.{IPlayer, PlayerCharacter}

class NormaTest extends munit.FunSuite {
    val StarsGoal: String = "Stars"
    val VictoriesGoal: String = "Victories"
    val name1: String = "player1"
    val maxHp: Int = 100
    val attack: Int = 10
    val defense: Int = 10
    val evasion: Int = 10

    var player: IPlayer = _

    override def beforeEach(context: BeforeEach): Unit = {
        player = new PlayerCharacter(name1, maxHp, attack, defense, evasion)
    }

    test("All players start the game with Norma 1"){
        assertEquals(player.getNorma, 1)
    }

    test("The goal can not be changed until it is completed"){
        //chose the goal
        assert(player.setGoal(StarsGoal))
        //can not be changed
        assert(!player.setGoal(VictoriesGoal))
        //complete the goal
        player.addStars(10)
        player.normaCheck()
        //try again
        assert(player.setGoal(VictoriesGoal))
    }

    test("The goal should be either 'Stars' or 'Victories'"){
        assert(!player.setGoal("Hp"))
        assert(!player.setGoal("stars"))
        assert(!player.setGoal("victories"))
        assert(player.setGoal(VictoriesGoal))
    }

    test("The players should have a goal to level up their Norma"){
        assert(!player.normaCheck())
        player.setGoal(StarsGoal)
        player.addStars(10)
        assert(player.normaCheck())
    }

    test("The level of the Norma should not be greater than 6"){
        player.addStars(500)
        for(_ <- 2 to 6){
            player.setGoal(StarsGoal)
            player.normaCheck()
        }
        player.setGoal(StarsGoal)
        assert(!player.normaCheck())
    }

    test("The player should only level up if the player has achieved the requirements of the current goal"){
        player.setGoal(StarsGoal)
        player.addStars(9)
        assert(!player.normaCheck())
    }
}
