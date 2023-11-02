package cl.uchile.dcc.citric
package model.unit

import model.unit.wildunit.{Chicken, RoboBall}

import model.unit.player.{PlayerCharacter, IPlayer}
import util.Random

class UnitTest extends munit.FunSuite {
    var unit: IUnit = _
    var player: IPlayer = _

    val maxHp: Int = 10
    val attack: Int = 10
    val defense: Int = 10
    val evasion: Int = 10

    override def beforeEach(context: BeforeEach): Unit = {
        unit = new Chicken(name = "John Doe", new Random(11))
        player = new PlayerCharacter("John Doe copy", maxHp, attack, defense, evasion, new Random(7))
    }

    // Two ways to test randomness (you can use any of them):

    // 1. Test invariant properties, e.g. the result is always between 1 and 6.
    test("A character should be able to roll a dice") {
        for (_ <- 1 to 10) {
            assert(unit.rollDice >= 1 && unit.rollDice <= 6)
        }
    }

    // 2. Set a seed and test the result is always the same.
    // A seed sets a fixed succession of random numbers, so you can know that the next numbers
    // are always the same for the same seed.
    test("A character should be able to roll a dice with a fixed seed") {
        val other =
            new PlayerCharacter(name="Pedro", maxHp, attack, defense, evasion, new Random(11))
        for (_ <- 1 to 10) {
            assertEquals(unit.rollDice(), other.rollDice())
        }
    }

    test("A Unit should not have less than 0 Stars"){
        //wildUnit
        assertEquals(unit.stars, 0)
        unit.stars = -10
        assertEquals(unit.stars, 0)
        //Player
        assertEquals(player.stars, 0)
        player.stars -= 10
        assertEquals(player.stars, 0)
    }

    test("A Unit should not have less than 0 hp"){
        assert(unit.hp > 0)
        unit.hp = -100
        assertEquals(unit.hp, 0)

        assert(player.hp > 0)
        player.hp -= maxHp
        assertEquals(player.hp, 0)
    }

    test("The hp should not be greater than maxHp") {
        assert(unit.hp <= unit.maxHp)
        unit.hp = unit.maxHp + 1
        assertEquals(unit.hp, unit.maxHp)

        assertEquals(player.hp, player.maxHp)
        player.hp += 1
        assertEquals(player.hp, player.maxHp)
    }

    test("An unit should be able to attack, and the damage should not be less than 0"){
        val prevHp: Int = unit.hp
        player.attack(unit)
        assert(unit.hp <= prevHp)
    }

    test("If a unit has been defeated, the attack method should return True"){
        /*  max{1, roll + attack - (roll + def) }
            should not be greater than ((6+attack) - (roll+1))
            so the unit can not defeat the player in 1 turn (). */
        assert(!unit.attack(player))
        // if the unit is defeated (hp == 0), returns True.
        unit.hp = 1
        assert(player.attack(unit) || unit.hp > 0)
    }

    test("If a unit has been defeated, the unit should not be attacked again."){
        unit.hp = 0
        assert(!player.attack(unit))
    }

    test("An unit should be able to evade, and the damage received should be greater or equal than 0"){
        val attack: Int = unit.attack + unit.rollDice()
        val prevHp: Int = player.hp
        player.evade(attack)
        assert(player.hp == prevHp || player.hp == math.max(0, prevHp - attack))
    }

    test("An unit should be able to defend, and the damage received should be greater than 0"){
        val attack: Int = unit.attack + unit.rollDice()
        val prevHp: Int = player.hp
        player.defend(attack)
        assert(player.hp < prevHp)
    }
}
