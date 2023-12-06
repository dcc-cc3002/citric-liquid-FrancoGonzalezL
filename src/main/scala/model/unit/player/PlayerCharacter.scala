package cl.uchile.dcc.citric
package model.unit.player

import model.unit.wildUnit.IWildUnit
import model.unit.{AUnit, IUnit}
import model.norma.INorma
import model.norma.levels.NormaLvl1
import model.panel.concretePanel.HomePanel

import cl.uchile.dcc.citric.model.panel.Panel
import cl.uchile.dcc.citric.observer.Observer

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
  * @param namePlayer The name of the player. This is an identifier and should be unique.
  * @param maxHpStat The maximum health points a player can have. It represents the player's endurance.
  * @param attackStat The player's capability to deal damage to opponents.
  * @param defenseStat The player's capability to resist or mitigate damage from opponents.
  * @param evasionStat The player's skill to completely avoid certain attacks.
  * @param randomNumberGenerator A utility to generate random numbers. Defaults to a new `Random`
  *                              instance.
  *
  * @author [[https://github.com/danielRamirezL/ Daniel Ramírez L.]]
  * @author [[https://github.com/joelriquelme/ Joel Riquelme P.]]
  * @author [[https://github.com/r8vnhill/ Ignacio Slater M.]]
  * @author [[https://github.com/Seivier/ Vicente González B.]]
  * @author [[https://github.com/frgonzal/ Franco González L.]]
  */
class PlayerCharacter(
     namePlayer: String,
     maxHpStat: Int,
     attackStat: Int,
     defenseStat: Int,
     evasionStat: Int,
     randomNumberGenerator: Random = new Random()

 ) extends AUnit(maxHpStat, randomNumberGenerator) with IPlayer {

    override def name: String = namePlayer

    override def maxHp: Int = maxHpStat

    override def attack: Int = attackStat

    override def defense: Int = defenseStat

    override def evasion: Int = evasionStat

    /** Victories counter. */
    private var _victories: Int = 0

    /** When a player is knockout the ko var sets to true */
    private var ko: Boolean = false

    /** Norma of the player */
    private var _norma: INorma = new NormaLvl1(player=this)

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

    override def goal: String = _norma.goal

    override def normaLvl: Int = _norma.normaLvl

    override def goal_=(option: String): Boolean = {
        _norma.goal = option
    }

    override def normaCheck(panel: HomePanel): Unit = {
        if (!panel.containsCharacter(this)) throw new AssertionError("Player should be on a HomePanel")

        val newNorma: Option[INorma] = _norma.normaCheck(panel)
        if(newNorma.isDefined) {
            _norma = newNorma.get
        }
        if(normaLvl == _norma.maxNormaLvl)
            notifyObservers(response=this)
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

    /* Set the hp equal to the maxHp */
    this.hp = maxHp


    private var observers: Array[Observer[IPlayer]] = Array()

    override def registerObserver(o: Observer[IPlayer]): Unit = {
        observers = observers :+ o
    }

    override def notifyObservers(response: IPlayer): Unit = {
        observers.foreach(o => {
            o.update(this, this)
        })
    }

    override def canReceiveInput: Boolean = true

    override def currentPanel: Option[Panel] = {
        _currentPanel
    }

    override def moveToPanel(panel: Panel): Unit = {
        if( currentPanel.isDefined) {
            if( !currentPanel.get.containsNextPanel(panel) )
                throw new AssertionError("Can move only to panels that are connected.")
            else
                currentPanel.get.removeCharacter(this)
        }

        panel.addCharacter(this)
        _currentPanel = Some(panel)
    }

    /* Variable to save the current Panel. Can be None. */
    private var _currentPanel: Option[Panel] = None
}