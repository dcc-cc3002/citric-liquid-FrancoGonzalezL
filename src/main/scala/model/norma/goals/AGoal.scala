package cl.uchile.dcc.citric
package model.norma.goals

/** Abstract class representing a Goal.
 *
 * @param _required The required value to achieve the goal.
 */
abstract class AGoal(_required: Int) extends IGoal {

    override def required: Int = _required
}
