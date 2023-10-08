package cl.uchile.dcc.citric
package model.unit.player

import model.unit.IUnit
import model.unit.wildunit.{Chicken, RoboBall, Seagull}

import scala.util.Random

class PlayerCharacterTest extends munit.FunSuite {
    /*
    REMEMBER: It is a good practice to use constants for the values that are used in multiple
    tests, so you can change them in a single place.
    This will make your tests more readable, easier to maintain, and less error-prone.
    */
    private val name = "testPlayer"
    private val maxHp = 10
    private val attack = 1
    private val defense = 1
    private val evasion = 1
    /* Add any other constants you need here... */

    /*
    This is the object under test.
    We initialize it in the beforeEach method so we can reuse it in all the tests.
    This is a good practice because it will reset the object before each test, so you don't have
    to worry about the state of the object between tests.
    */
    private var character: IPlayer = _  // <- x = _ is the same as x = null
    /* Add any other variables you need here... */

    // This method is executed before each `test(...)` method.
    override def beforeEach(context: BeforeEach): Unit = {
        character = new PlayerCharacter(
            name,
            maxHp,
            attack,
            defense,
            evasion,
            new Random(11)
        )
    }

    test("A character should have correctly set their attributes") {
        assertEquals(character.name, name)
        assertEquals(character.maxHp, maxHp)
        assertEquals(character.attack, attack)
        assertEquals(character.defense, defense)
        assertEquals(character.evasion, evasion)
    }



    test("If a character's hp reaches 0, then the character will be KO"){
        character.hp -= character.maxHp+1
        assert(character.isKO)
    }

    test("The player must roll a die and obtain a number greater than or equal to the required amount to recover"){
        character.hp = 0
        assert(character.isKO)
        character.recovery(required=0)
        assert(!character.isKO)
    }

    test("If the player defeats a wildUnit, the player should receive 1 victory"){
        val unit: IUnit = new Chicken
        val prevVictories: Int = character.victories
        unit.hp = 0     // hp == 0 => has been defeated
        assertEquals(unit.hp, 0)
        assert(unit.defeated(character))
        assertEquals(character.victories, prevVictories + 1)
    }

    test("If the player defeats another player, the player should receive 2 victories"){
        val otherPlayer: IUnit = new PlayerCharacter(name="John Doe", maxHp, attack, defense, evasion)
        val prevVictories: Int = character.victories
        otherPlayer.hp = 0    // hp == 0 => has been defeated
        assert(otherPlayer.defeated(character))
        assertEquals(character.victories, prevVictories + 2)
    }

    test("If the player receives an attack, the player should be able to defend or evade"){
        val unit: IUnit = new Chicken(name="Huevito Rey")
        val attack: Int = unit.attack + unit.rollDice()
        val prevHp: Int = character.hp
        character.receiveAttack(attack)
        assert(character.hp <= prevHp)


    }

}
