package cl.uchile.dcc.citric
package model.unit

/** Represents the entities tha will participate int the game,
 * either as a character, controllable by the user, or as a
 * Wild Unit, not controllable by the user
 *
 * @author [[https://github.com/FrancoGonzalezL Franco González L.]]
 */
trait IUnit {

    /** The name of the unit */
    val name: String

    /** The max hit points of the unit */
    val maxHp: Int

    /** The attack points of the unit */
    val attack: Int

    /** The defence points of the unit */
    val defense: Int

    /** The evasion points of the unit */
    val evasion: Int

    /** Method to increase the stars of the unit.
     *
     * @param amount The amount of stars to add.
     */
    def addStars(amount: Int): Unit

    /** Method to reduce the stars of the unit.
     *
     * @param amount The amount of stars to remove.
     */
    def removeStars(amount: Int): Unit

    /** Method to get the current amount of stars.*/
    def getStars: Int

    /** Return the current amount of hit points */
    def getHp: Int

    /** Increases the amount of hit points.
     *
     * @param amount The amount to add. It must be
     *               greater than or equal to 0.
     *               If the result is greater than the max
     *               hit points then set the HP equal to
     *               the maxHP.
     */
    def increaseHp(amount: Int): Unit

    /** Reduces the amount of current hit points.
     *
     * @param amount the amount to remove from the hit points.
     *               If the result is less than 0, then set
     *               the HP equal to 0.
     */
    def reduceHp(amount: Int): Unit
}
