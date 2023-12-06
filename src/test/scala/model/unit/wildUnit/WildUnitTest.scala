package cl.uchile.dcc.citric
package model.unit.wildUnit

import model.unit.player.{IPlayer, PlayerCharacter}
import model.unit.IUnit

import model.unit.wildUnit.concreteWildUnit.{Chicken, RoboBall, Seagull}

import util.Random

class WildUnitTest extends munit.FunSuite{
    var player: IPlayer = _
    var chicken: IUnit  = _
    var roboBall: IUnit = _
    var seagull: IUnit  = _
    private val name = "testPlayer"
    private val maxHp = 10
    private val attack = 1
    private val defense = 1
    private val evasion = 1

    override def beforeEach(context: BeforeEach): Unit = {
        player = new PlayerCharacter(
            name,
            maxHp,
            attack,
            defense,
            evasion,
            new Random(11)
        )
        chicken  = new Chicken(name="Pedro")
        roboBall = new RoboBall(name="Juan")
        seagull  = new Seagull(name="Diego")
    }

    test("A chicken should have correctly set its attributes"){
        assertEquals(chicken.maxHp, 3)
        assertEquals(chicken.attack, -1)
        assertEquals(chicken.defense, -1)
        assertEquals(chicken.evasion, 1)
    }
    test("A RoboBall should have correctly set its attributes") {
        val roboBall: IWildUnit = new RoboBall()
        assertEquals(roboBall.maxHp, 3)
        assertEquals(roboBall.attack, -1)
        assertEquals(roboBall.defense, 1)
        assertEquals(roboBall.evasion, -1)
    }
    test("A Seagull should have correctly set its attributes") {
        val seagull: IWildUnit = new Seagull()
        assertEquals(seagull.maxHp, 3)
        assertEquals(seagull.attack, 1)
        assertEquals(seagull.defense, -1)
        assertEquals(seagull.evasion, -1)
    }

    test("If a Wild Unit defeats a Player, the Wild Unit gets half the stars of the player") {
        player.stars = 11
        val prevStarsUnit: Int = chicken.stars
        val prevStarsPlayer: Int = player.stars

        player.hp = 0
        assert(player.isKO)
        player.defeated(chicken)

        val expectedStarsChicken: Int = prevStarsUnit + (prevStarsPlayer / 2)
        val expectedStarsPlayer: Int = prevStarsPlayer - (prevStarsPlayer / 2)

        assertEquals(chicken.stars, expectedStarsChicken)
        assertEquals(player.stars, expectedStarsPlayer)
    }

    test("If a Wild Unit defeats another Wild Unit, The winner should get all the stars from the defeated and the bonus"){
        chicken.stars = 11
        chicken.hp = 0

        val prevStarsChicken: Int = chicken.stars
        val prevStarsSeagull: Int = seagull.stars
        val bonusChicken: Int = 3

        assertEquals(chicken.hp, 0)
        assertEquals(chicken.stars, 11)

        chicken.defeated(seagull)
        assertEquals(chicken.stars, 0)
        assertEquals(seagull.stars, prevStarsChicken + prevStarsSeagull + bonusChicken)
    }
}
