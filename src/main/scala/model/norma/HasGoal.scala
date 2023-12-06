package cl.uchile.dcc.citric
package model.norma


/** Represent that has a goal.
 *
 * A goal can be either got or set.
 *
 * A new goal can only be set when there is no existing goal.
 */
trait HasGoal {

    /** Returns the name of the current goal.
     *
     *  if a goal has not been set, this return an empty string.
     */
    def goal: String

    /** Returns the current level of the Norma */
    def normaLvl: Int

    /** Updates the goal of the player.
     *
     * @param option The goal selected by the player. It Must be
     *               either 'Stars' or 'Victories'.
     */
    def goal_=(option: Int): Boolean
}
