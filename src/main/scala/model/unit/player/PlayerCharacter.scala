package cl.uchile.dcc.citric
package model.unit.player

import model.unit.wildUnit.IWildUnit
import model.unit.{AUnit, IUnit}
import model.norma.{ANorma, INorma}
import model.panel.Panel
import cl.uchile.dcc.citric.model.norma.levels.NormaLvl1

import cl.uchile.dcc.citric.model.panel.home.{Home, HomePanel}

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
  * @param _name The name of the player. This is an identifier and should be unique.
  * @param _maxHp The maximum health points a player can have. It represents the player's endurance.
  * @param _attack The player's capability to deal damage to opponents.
  * @param _defense The player's capability to resist or mitigate damage from opponents.
  * @param _evasion The player's skill to completely avoid certain attacks.
  * @param _randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  *
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  * @author [[https://github.com/frgonzal/ Franco González L.]]
  */
class PlayerCharacter(
     _name: String,
     _maxHp: Int,
     _attack: Int,
     _defense: Int,
     _evasion: Int,
     _randomNumberGenerator: Random = new Random()
 ) extends AUnit(_maxHp, _randomNumberGenerator) with IPlayer {

    /** Victories counter. */
    private var _victories: Int = 0

    /** When a player is knockout the ko var sets to true */
    private var ko: Boolean = false

    /** Norma of the player */
    private var _Norma: INorma = new NormaLvl1(player=this)

    override def victories: Int = _victories

    override def victories_=(newAmount: Int): Boolean = {
        _victories = math.max(0, newAmount)
        true
    }

    override def defeated(attacker: IUnit): Unit = {
        if(hp > 0) return
        attacker.rewardFromPlayer(this)
    }

    override def isKO: Boolean = ko

    /** Reduces the amount of current hit points.
     *
     *  When a players HP is 0, the ko var sets to true.
     */
    override def hp_=(amount: Int): Unit = {
        super.hp_=(amount)
        if(this.hp == 0)
            ko = true
    }

    override def recovery(required: Int): Unit = {
        if(rollDice() >= required) {
            this.hp = this.maxHp
            ko = false
        }
    }

    override def goal: String = _Norma.goal

    override def normaLvl: Int = _Norma.normaLvl

    override def goal_=(option: String): Boolean = {
        _Norma.goal = option
    }

    override def normaCheck(panel: Home): Unit = {
        if (!panel.containsCharacter(this)) throw new AssertionError("Player should be on a HomePanel")

        val newNorma: Option[INorma] = _Norma.normaCheck(panel)
        if(newNorma.isDefined)
            _Norma = newNorma.get
    }

    override def rewardFromWildUnit(defeated: IWildUnit): Unit = {
        if (defeated.hp > 0) return
        val victoriesReward: Int = 1
        val starsReward:     Int = defeated.stars + defeated.bonusStars

        this.victories += victoriesReward
        this.stars     += starsReward
        defeated.stars  = 0
    }

    override def rewardFromPlayer(defeated: IPlayer): Unit = {
        if (defeated.hp > 0) return
        val victoriesReward: Int = 2
        val starsReward:     Int = defeated.stars / 2

        this.victories += victoriesReward
        this.stars     += starsReward
        defeated.stars -= starsReward
    }

    override def name: String = _name

    override def maxHp: Int = _maxHp

    override def attack: Int = _attack

    override def defense: Int = _defense

    override def evasion: Int = _evasion

    /* Set the hp equal to the maxHp */
    this.hp = maxHp
}