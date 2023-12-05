package cl.uchile.dcc.citric
package factory

import model.unit.player.IPlayer
import factory.unit.{PlayerFactory, UnitFactory, WildUnitFactory}

import cl.uchile.dcc.citric.exceptions.FactoryConfigurationException
import cl.uchile.dcc.citric.model.unit.IUnit
import cl.uchile.dcc.citric.model.unit.wildUnit.IWildUnit
import org.junit.Assert

class UnitFactoryTest extends munit.FunSuite {
    var playerFactory: UnitFactory[IPlayer] = _
    var wildUnitFactory: UnitFactory[IWildUnit] = _

    override def beforeEach(context: BeforeEach): Unit = {

    }

    test("Using a Factory without initializing the variables should throw an exception") {
        playerFactory = new PlayerFactory
        wildUnitFactory = new WildUnitFactory

        /* Test all transitions */
        Assert.assertThrows(classOf[FactoryConfigurationException], () => playerFactory.createUnit("A"))
        Assert.assertThrows(classOf[FactoryConfigurationException], () => wildUnitFactory.createUnit("B"))
    }

    test("A Factory will generate Different Units. "){
        wildUnitFactory.setRandomStats()
        val wildUnit1: IUnit = wildUnitFactory.createUnit("Pedro")
        wildUnitFactory.setRandomStats()
        val wildUnit2: IUnit = wildUnitFactory.createUnit("Pedro")

        assertNotEquals(wildUnit1, wildUnit2)

        playerFactory.setRandomStats()
        val player1: IUnit = playerFactory.createUnit("Pedro")
        playerFactory.setRandomStats()
        val player2: IUnit = playerFactory.createUnit("Pedro")

        assertNotEquals(player1, player2)
    }

    test("The WildUnit Factory will generate Random WildUnits"){
        wildUnitFactory.setRandomStats()
        val wildUnit1: IUnit = wildUnitFactory.createUnit("Pedro")
        wildUnitFactory.setRandomStats()
        val wildUnit2: IUnit = wildUnitFactory.createUnit("Pedro")

        assertNotEquals(wildUnit1.getClass.toString, wildUnit2.getClass.toString)
    }

    test("The Unit generated will have the same stats as the previous one if they are not generated again. "){
        wildUnitFactory.setRandomStats()
        val wildUnit1: IUnit = wildUnitFactory.createUnit("A")
        val wildUnit2: IUnit = wildUnitFactory.createUnit("B")
        assertEquals(wildUnit1.maxHp, wildUnit2.maxHp)
        assertEquals(wildUnit1.attack, wildUnit2.attack)
        assertEquals(wildUnit1.defense, wildUnit2.defense)
        assertEquals(wildUnit1.evasion, wildUnit2.evasion)
    }
}
