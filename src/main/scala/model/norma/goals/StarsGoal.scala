package cl.uchile.dcc.citric
package model.norma.goals

import model.unit.player.IPlayer


/** Class representing the Stars goal.
 *
 * To check if the goal has been achieved, the stars of the player should be compared
 * to the required value.
 *
 * @param _required The value required to achieve the goal.
 * @param player    The player that has the goal.
 */
class StarsGoal(_required: Int, player: IPlayer) extends AGoal(_required) {

    override def toString: String = "Stars"

    override def achievedRequirements: Boolean = {
        if(player.stars >= required)
            true
        else
            false
    }
}
