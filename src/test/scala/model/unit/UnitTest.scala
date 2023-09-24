package cl.uchile.dcc.citric
package model.unit

import model.unit.wildunit.Chicken

class UnitTest extends munit.FunSuite {
    var unit: IUnit = _

    override def beforeEach(context: BeforeEach): Unit = {
        unit = new Chicken(name = "John Doe")
    }

    test("The addStars method should add the correct amount"){
        assertEquals(unit.stars, 0)
        unit.addStars(10)
        assertEquals(unit.stars, 10)
    }

    test("A Unit can not have less than 0 Stars"){
        assertEquals(unit.stars, 0)
        unit.removeStars(10)
        assertEquals(unit.stars, 0)
    }

    test("The addStars and removeStars methods should not be called with negative parameters"){
        assertEquals(unit.stars, 0)
        unit.addStars(10)
        assertEquals(unit.stars, 10)
        unit.addStars(-10)
        assertEquals(unit.stars, 10)
        unit.removeStars(-10)
        assertEquals(unit.stars, 10)
    }

    test("A Unit should not have less than 0 hp"){
        assertEquals(unit.hp, 3)
        unit.reduceHp(10)
        assertEquals(unit.hp, 0)
    }

    test("The hp should not be greater than maxHp") {
        unit.increaseHp(unit.maxHp + 1)
        assertEquals(unit.hp, unit.maxHp)
    }

    test("The increaseHp and reduceHp methods should add and reduce the correct amount of hp"){
        assertEquals(unit.hp, 3)
        unit.reduceHp(2)
        assertEquals(unit.hp, 1)
        unit.increaseHp(1)
        assertEquals(unit.hp, 2)
    }

    test("The increaseHp and reduceHp methods should not be called with negative parameters"){
        unit.reduceHp(2)
        assertEquals(unit.hp, 1)
        unit.increaseHp(-10)
        assertEquals(unit.hp, 1)
        unit.reduceHp(-10)
        assertEquals(unit.hp, 1)
    }
}
