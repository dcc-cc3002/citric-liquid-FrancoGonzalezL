package cl.uchile.dcc.citric
package model.panel

import model.unit.IUnit
import model.unit.wildunit.{Chicken, Seagull, RoboBall}

import scala.util.Random

import model.unit.player.IPlayer

/**A class representing an Encounter Panel.
 *
 * When landing on one, the player will enter into battle with a random Wild Unit.
 */
class EncounterPanel extends APanel {

    /** Current wildUnit on the Panel */
    var wildUnit: Option[IUnit] = None

    /** Generates a new WildUnit on the Panel */
    private def setUnit(): Unit = {
        val num: Int = (new Random()).nextInt(3)
        if(num == 0){
            wildUnit = Some(new Chicken())
        }else if(num == 1){
            wildUnit = Some(new RoboBall())
        }else{
            wildUnit = Some(new Seagull())
        }
    }

    /** Returns true if the wildUnit is None or if the wildUnit is dead. */
    private def checkUnit(): Boolean = {
        if(wildUnit.isEmpty)
            true
        else if(wildUnit.get.getHp == 0)
            true
        else false
    }

    /* Provisional method */
    /**Generates a random Wild Unit and calculates the fight between the player and the unit.
     *
     * @param player The player that has moved to this Panel.
     */
    override def effect(player: IPlayer): Unit = {
        if(checkUnit())
            setUnit()
    }
}