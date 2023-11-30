package cl.uchile.dcc.citric
package model.unit

import cl.uchile.dcc.citric.exceptions.InvalidInputException

import util.Random

/** Abstract class representing a Unit. */
abstract class AUnit(
    /* This val is use to set the initial hp equal to maxHp*/
    _maxHp: Int,

    /** The number generator used for the rollDice method */
    val randomNumberGenerator: Random = new Random()

) extends IUnit {

    /** Current amount of hit points */
    private var _hp: Int = _maxHp

    /** Current amount of stars */
    private var _stars: Int = 0

    override def stars: Int = _stars

    override def stars_=(newAmount: Int): Unit = {
        _stars = math.max(0, newAmount)
    }

    override def hp: Int = _hp

    override def hp_=(newHp: Int): Unit = {
        _hp = math.min(maxHp, math.max(0, newHp))
    }

    override def rollDice(): Int = randomNumberGenerator.nextInt(6) + 1

    override def attack(unit: IUnit): Int = {
        if(hp == 0 || unit.hp == 0) 0
        math.max(0, attack + rollDice())
    }

    override def defend(attack: Int): Unit = {
        val totalAttack: Int = math.max(1, attack - (rollDice() + this.defense))
        this.hp -= totalAttack
    }

    override def evade(attack: Int): Unit = {
        val evasion: Int = this.evasion + rollDice()
        if ( !(evasion > attack) )
            this.hp -= attack
    }

    override def receiveAttack(attack: Int, defenseMethod: Int): Unit = {
        if(this.hp == 0) return

        if (defenseMethod == 1)
            defend(attack)
        else if (defenseMethod == 2)
            evade(attack)
        else
            throw new InvalidInputException("Input should be 1 or 2.")
    }
}
