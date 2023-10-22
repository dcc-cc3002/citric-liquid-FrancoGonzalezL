package cl.uchile.dcc.citric
package model.norma.goals

import model.unit.player.IPlayer


/** Class representing the Victories goal.
 *
 * To check if the goal has been achieved, the victories of the player should be compared
 * to the required value.
 *
 * @param _required The value required to achieve the goal.
 * @param player    The player that has the goal.
 */
class VictoriesGoal(_required: Int, player: IPlayer) extends AGoal(_required) {

    override def toString: String = "Victories"

    override def achievedRequirements: Boolean = {
        if(player.victories >= required)
            true
        else
            false
    }
}
