package cl.uchile.dcc.citric
package model.norma

/** Represent the Norma of the player.
 *
 * A player has to achieve Norma 6 to win the game.
 *
 * When the requirement to achieve a Norm is met, the player must pass through
 * a Home Panel to activate the Norm Check effect and thus level up. This is known as Norm Clear.
 *
 * The player can choose one of two objectives to level up their Norm: reaching a certain number
 * of stars or achieving a specific number of victories. The objective must be chosen once the
 * player levels up and cannot be changed until it is completed.
 *
 */
trait INorma {

    /** Returns the current Norma level */
    def getNorma: Int

    /** Sets the goal.
     *
     * @param option The goal selected by the player. It Must be
     *               either 'Stars' or 'Victories'.
     *
     * @return True if the goal has been successfully settled.
     */
    def setGoal(option: String): Boolean

    /** Checks if the player has achieved the requirements to level up the Norma.
     *
     * For the time being, this implementation returns true if the level increase was achieved;
     * otherwise, it returns false.
     *
     * @return True if the norma level has successfully increased.
     */
    def normaCheck(): Boolean
}
