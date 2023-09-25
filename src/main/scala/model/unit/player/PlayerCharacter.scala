package cl.uchile.dcc.citric
package model.unit.player

import model.unit.wildunit.WildUnit
import model.unit.{AUnit, IUnit}
import model.norma.{INorma, Norma}
import model.panel.Panel

import scala.util.Random

/** The `PlayerCharacter` class represents a character or avatar in the game, encapsulating
  * several attributes such as health points, attack strength, defense capability,
  * and evasion skills. Each player has a unique name, and throughout the game,
  * players can collect stars, roll dice, and progress in levels known as 'norma'.
  * This class not only maintains the state of a player but also provides methods
  * to modify and interact with these attributes.
  *
  * For instance, players can:
  *
  * - Increase or decrease their star count.
  *
  * - Roll a dice, a common action in many board games.
  *
  * - Advance their norma level.
  *
  * Furthermore, the `Player` class has a utility for generating random numbers,
  * which is primarily used for simulating dice rolls. By default, this utility is
  * an instance of the `Random` class but can be replaced if different random
  * generation behaviors are desired.
  *
  * @param name The name of the player. This is an identifier and should be unique.
  * @param maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param attack The player's capability to deal damage to opponents.
  * @param defense The player's capability to resist or mitigate damage from opponents.
  * @param evasion The player's skill to completely avoid certain attacks.
  * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  *
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  * @author [[https://github.com/FrancoGonzalezL/ Franco González L.]]
  */
class PlayerCharacter(override val name: String,
                      override val maxHp: Int,
                      override val attack: Int,
                      override val defense: Int,
                      override val evasion: Int,

                     /** The number generator used for the rollDice method */
                      val randomNumberGenerator: Random = new Random())
    extends AUnit(maxHp) with IPlayer {

    /** Victories counter. */
    private var _victories: Int = 0

    /** When a player is knockout the ko var sets to true */
    private var ko: Boolean = false

    /** Norma of the player */
    private val Norma: INorma = new Norma(this)

    override def victories: Int = _victories

    /* Provisional method */
    override def addVictories(unit: IUnit): Unit = {
        if(unit.isInstanceOf[WildUnit])
            _victories += 1
        if(unit.isInstanceOf[PlayerCharacter])
            _victories += 2
    }

    override def rollDice(): Int = randomNumberGenerator.nextInt(6) + 1

    override def isKO: Boolean = ko

    /** Reduces the amount of current hit points.
     *
     * @param amount the amount to remove from the hit points.
     *               If the result is less than 0, then set
     *               the HP equal to 0.
     *
     * When a players HP is 0, the ko var sets to true.
     */
    override def reduceHp(amount: Int): Unit = {
        super.reduceHp(amount)
        if(this.hp == 0)
            ko = true
    }

    override def recovery(required: Int): Unit = {
        if(rollDice() >= required)
            ko = false
    }

    override def norma: Int = Norma.norma

    override def goal: String = Norma.goal

    override def goal_=(option: String): Boolean = {
        Norma.goal = option
    }

    override def normaCheck(panel: Panel): Boolean = Norma.normaCheck(panel: Panel)
}
