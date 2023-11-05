package cl.uchile.dcc.citric
package model.unit
package wildunit

import model.unit.player.IPlayer
import util.Random


/** Abstract class representing the Wild Units. */
abstract class AWildUnit(hpInitVal: Int, random: Random) extends AUnit(hpInitVal: Int, random: Random) with IWildUnit {

    override def defeated(attacker: IUnit): Boolean = {
        if(hp > 0) return false
        attacker.getRewardFromWildUnit(defeated=this)
    }

    override def getRewardFromWildUnit(defeated: IWildUnit): Boolean = {
        if(defeated.hp > 0) return false

        this.stars += defeated.stars + defeated.bonusStars
        defeated.stars = 0
        true
    }

    override def getRewardFromPlayer(defeated: IPlayer): Boolean = {
        if (defeated.hp > 0) return false

        this.stars += defeated.stars/2
        defeated.stars -= defeated.stars/2
        true
    }

    override def receiveAttack(attack: Int): Boolean = {
        if(this.defense + 3 > this.attack)
            this.defend(attack)
        else
            this.evade(attack)
    }
}
