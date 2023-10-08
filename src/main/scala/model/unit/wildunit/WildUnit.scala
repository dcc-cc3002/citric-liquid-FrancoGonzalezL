package cl.uchile.dcc.citric
package model.unit
package wildunit

import cl.uchile.dcc.citric.model.unit.player.IPlayer

import util.Random

/** Abstract class representing the Wild Units. */
abstract class WildUnit(hpInitVal: Int, random: Random) extends AUnit(hpInitVal: Int, random: Random) {

    override def defeated(player: IPlayer): Boolean = {
        if(hp > 0)
            false
        else{
            player.victories += 1
            player.stars += this.stars
            this.stars    = 0
            true
        }
    }

    override def receiveAttack(attack: Int): Boolean = {
        val rand: Int = rollDice()
        // random choice
        if(rand > 3)
            this.defend(attack)
        else
            this.evade(attack)
        true
    }
}
