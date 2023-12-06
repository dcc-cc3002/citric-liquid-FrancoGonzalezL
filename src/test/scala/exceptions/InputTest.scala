package cl.uchile.dcc.citric
package exceptions

import model.unit.IUnit
import org.junit.Assert
import model.unit.wildUnit.concreteWildUnit.Chicken

class InputTest extends munit.FunSuite {

    test("When a Unit has to receive and attack, the input should be 1 or 2"){
        val unit1: IUnit = new Chicken("Good Chicken")
        val unit2: IUnit = new Chicken("Evil Chicken")
        val attack: Int = unit2.attack(unit1)
        val defenseMethod1: Int = 0
        val defenseMethod2: Int = 3

        Assert.assertThrows(classOf[InvalidInputException], () => unit1.receiveAttack(attack, defenseMethod1))
        Assert.assertThrows(classOf[InvalidInputException], () => unit1.receiveAttack(attack, defenseMethod2))
    }
}
