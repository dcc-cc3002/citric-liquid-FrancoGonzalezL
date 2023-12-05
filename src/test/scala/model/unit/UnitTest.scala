package cl.uchile.dcc.citric
package model.unit

import model.unit.player.PlayerCharacter
import model.unit.wildUnit.concreteWildUnit.Chicken

import cl.uchile.dcc.citric.exceptions.InvalidInputException

import util.Random

class UnitTest extends munit.FunSuite {
    var chicken: IUnit = _
    var chickenKiller: IUnit = _

    val maxHp:   Int = 10
    val attack:  Int = 10
    val defense: Int = 10
    val evasion: Int = 10

    override def beforeEach(context: BeforeEach): Unit = {
        chicken = new Chicken("Evil Chicken", new Random(11))
        chickenKiller = new PlayerCharacter("Chicken Killer", maxHp, attack, defense, evasion, new Random(7))
    }

    // Two ways to test randomness (you can use any of them):

    // 1. Test invariant properties, e.g. the result is always between 1 and 6.
    test("A character should be able to roll a dice") {
        for (_ <- 1 to 10) {
            assert(chicken.rollDice >= 1 && chicken.rollDice <= 6)
        }
    }

    // 2. Set a seed and test the result is always the same.
    // A seed sets a fixed succession of random numbers, so you can know that the next numbers
    // are always the same for the same seed.
    test("A character should be able to roll a dice with a fixed seed") {
        val other =
            new PlayerCharacter("Pedro", maxHp, attack, defense, evasion, new Random(11))
        for (_ <- 1 to 10) {
            assertEquals(chicken.rollDice(), other.rollDice())
        }
    }

    test("A Unit should not have less than 0 Stars"){
        val expected: Int = 0
        val newStars: Int = -10

        //wildUnit
        assertEquals(chicken.stars, expected)
        chicken.stars = newStars
        assertEquals(chicken.stars, expected)

        //Player
        assertEquals(chickenKiller.stars, expected)
        chickenKiller.stars = newStars
        assertEquals(chickenKiller.stars, expected)
    }

    test("A Unit should not have less than 0 hp"){
        val expected: Int = 0
        val newHp: Int = -10

        assert(chicken.hp > expected)
        assert(chickenKiller.hp > 0)

        chicken.hp = newHp
        assertEquals(chicken.hp, expected)

        chickenKiller.hp = newHp
        assertEquals(chickenKiller.hp, expected)
    }

    test("The hp should not be greater than maxHp") {
        assert(chicken.hp <= chicken.maxHp)
        assert(chickenKiller.hp <= chickenKiller.maxHp)

        chicken.hp = chicken.maxHp + 1
        assertEquals(chicken.hp, chicken.maxHp)

        chickenKiller.hp = chickenKiller.maxHp + 1
        assertEquals(chickenKiller.hp, chickenKiller.maxHp)
    }

    test("If a unit has been defeated, the unit should not be attacked again."){
        val prevHp: Int = chicken.hp
        var attack: Int = chickenKiller.attack(chicken)
        val defenseMethod: Int = 1
        val expected: Int = 0

        chicken.receiveAttack(attack, defenseMethod)
        assertNotEquals(chicken.hp, prevHp)

        attack = chickenKiller.attack(chicken)
        chicken.receiveAttack(attack, defenseMethod)
        assertEquals(chicken.hp, expected)
    }

    test("An unit should be able to attack, and the damage should not be less than 0") {
        val prevH: Int = chickenKiller.hp
        val attack: Int = chicken.attack(chickenKiller)
        val defenseMethod: Int = 1

        chicken.receiveAttack(attack, defenseMethod)
        assert(chicken.hp <= prevH)
    }

    test("An unit should be able to defend, and the damage received should be greater than 0"){
        val prevHp: Int = chickenKiller.hp
        val attack: Int = chicken.attack(chickenKiller)
        val defendMethod: Int = 1

        chickenKiller.receiveAttack(attack, defendMethod)
        assert(chickenKiller.hp < prevHp)
    }

    test("An unit should be able to evade, and the damage received should be greater or equal than 0"){
        val prevHp: Int = chickenKiller.hp
        val attack: Int = chickenKiller.attack(chicken)
        val defendMethod: Int = 2

        chicken.receiveAttack(attack, defendMethod)
        assert(chickenKiller.hp == prevHp || chickenKiller.hp == math.max(0, prevHp - attack))
    }
}
