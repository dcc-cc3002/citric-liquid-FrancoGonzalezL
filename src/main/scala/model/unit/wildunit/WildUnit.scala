package cl.uchile.dcc.citric
package model.unit
package wildunit

import model.unit.player.IPlayer
import util.Random


/** Abstract class representing the Wild Units. */
abstract class WildUnit(hpInitVal: Int, random: Random) extends AUnit(hpInitVal: Int, random: Random) {

    /** The bonus of stars of the wildUnit. */
    protected def extraStars: Int

    override def defeated(player: IPlayer): Boolean = {
        if(hp > 0)
            false
        else{
            player.victories += 1
            player.stars += this.stars + extraStars
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
