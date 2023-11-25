package cl.uchile.dcc.citric
package model.unit

import util.Random

/** Abstract class representing a Unit.
 *
 * @param _hp The current hp of the player. In order to initialize the hp
 *            equals to the maxHp, it must be received as a parameter.
 */
abstract class AUnit(

            /** Current amount of hit points */
            private var _hp: Int,

            /** The number generator used for the rollDice method */
            val randomNumberGenerator: Random = new Random()

            ) extends IUnit {

    /** Current amount of stars */
    private var _stars: Int = 0

    override def stars: Int = _stars

    override def stars_=(newAmount: Int): Unit = {
        _stars = math.max(0, newAmount)
    }

    override def hp: Int = _hp

    override def hp_=(newHp: Int): Unit = {
        //  0 <= newHp <= maxHp
        _hp = math.min(maxHp, math.max(0, newHp))
    }

    override def rollDice(): Int = randomNumberGenerator.nextInt(6) + 1

    override def attack(unit: IUnit): Boolean = {
        if(hp == 0 || unit.hp == 0) false
        else{
            val attackVal: Int = math.max(0, attack + rollDice())
            unit.receiveAttack(attackVal)
        }
    }

    override def defend(attack: Int): Boolean = {
        this.hp -= math.max(1, attack - (rollDice() + this.defense))
        if(hp == 0) true
        else        false
    }

    override def evade(attack: Int): Boolean = {
        if (rollDice() + this.evasion <= attack)
            hp -= attack
        if(hp == 0) true
        else        false

    }

}
