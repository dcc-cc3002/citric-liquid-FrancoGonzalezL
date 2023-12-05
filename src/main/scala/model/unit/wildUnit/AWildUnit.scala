package cl.uchile.dcc.citric
package model.unit
package wildUnit

import model.unit.player.IPlayer
import util.Random


/** Abstract class representing the Wild Units. */
abstract class AWildUnit(maxHpStat: Int, randomNumberGenerator: Random)
    extends AUnit(maxHpStat, randomNumberGenerator) with IWildUnit {

    override def defeated(attacker: IUnit): Unit = {
        if(hp > 0) return
        attacker.rewardFromWildUnit(defeated = this)
    }

    override def rewardFromWildUnit(defeated: IWildUnit): Unit = {
        if(defeated.hp > 0) return
        val totalStars: Int = defeated.stars + defeated.bonusStars

        this.stars    += totalStars
        defeated.stars = 0
    }

    override def rewardFromPlayer(defeated: IPlayer): Unit = {
        if (defeated.hp > 0) return
        val totalStars: Int = defeated.stars / 2

        this.stars     += totalStars
        defeated.stars -= totalStars
    }
}
