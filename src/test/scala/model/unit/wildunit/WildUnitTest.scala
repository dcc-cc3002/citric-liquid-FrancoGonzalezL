package cl.uchile.dcc.citric
package model.unit.wildunit

class WildUnitTest extends munit.FunSuite{

    test("A chicken should have correctly set its attributes"){
        val chicken: WildUnit = new Chicken()
        assertEquals(chicken.maxHp, 3)
        assertEquals(chicken.attack, -1)
        assertEquals(chicken.defense, -1)
        assertEquals(chicken.evasion, 1)
    }
    test("A RoboBall should have correctly set its attributes") {
        val roboBall: WildUnit = new RoboBall()
        assertEquals(roboBall.maxHp, 3)
        assertEquals(roboBall.attack, -1)
        assertEquals(roboBall.defense, 1)
        assertEquals(roboBall.evasion, -1)
    }
    test("A Seagull should have correctly set its attributes") {
        val seagull: WildUnit = new Seagull()
        assertEquals(seagull.maxHp, 3)
        assertEquals(seagull.attack, 1)
        assertEquals(seagull.defense, -1)
        assertEquals(seagull.evasion, -1)
    }
}
