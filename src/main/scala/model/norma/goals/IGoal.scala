package cl.uchile.dcc.citric
package model.norma.goals

/** Represents a goal.
 *
 * There are two goals available, Stars or Victories.
 */
trait IGoal {

    /** Returns the name of the goal */
    def toString: String

    /** When a goal is set, a required value has to be achieved to finish the goal.
     *
     * The required value should on the current lvl of the norma and the current goal.
     *
     * @return The value required.
     */
    def required: Int

    /** This should be useful to know if the goal has been completed.
     *
     * @return True if the current value of the goal is equal or greater than the required.
     */
    def achievedRequirements: Boolean
}
