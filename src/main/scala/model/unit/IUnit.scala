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

    /** Return the current amount of hit points */
    def hp: Int

    /** Method to get the current amount of stars. */
    def stars: Int

    /** Adds stars to the unit.
     *
     * @param amount The amount of stars to add.
     */
    def addStars(amount: Int): Unit

    /** Removes stars from the unit.
     *
     * @param amount The amount of stars to remove.
     */
    def removeStars(amount: Int): Unit

    /** Increases the amount of hit points.
     *
     * The hp should not be greater than maxHp.
     *
     * @param amount The amount to add to the hp.
     *               It should be positive.
     */
    def increaseHp(amount: Int): Unit

    /** Reduces the amount of current hit points.
     *
     * The hp should not be less than 0.
     *
     * @param amount The amount to remove from the hp.
     *               It should be positive.
     */
    def reduceHp(amount: Int): Unit
}
