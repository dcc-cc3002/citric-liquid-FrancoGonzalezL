package cl.uchile.dcc.citric
package model.norma

import model.panel.Panel

/** Represents the Norma of the player.
 *
 * The player can choose one of two objectives to level up their Norm: reaching a certain number
 * of stars or achieving a specific number of victories. The objective must be chosen once the
 * player levels up and cannot be changed until it is completed.
 *
 * When the requirement to achieve a Norm is met, the player must pass through
 * a Home Panel to activate the Norm Check effect and thus level up. This is known as Norm Clear.
 *
 * A player has to achieve Norma 6 to win the game.
 */
trait INorma {

    /** Returns the current Norma level */
    def norma: Int

    /** Returns the current goal of the player */
    def goal: String

    /** Updates the goal of the player.
     *
     * @param option The goal selected by the player. It Must be
     *               either 'Stars' or 'Victories'.
     */
    def goal_=(option: String): Boolean

    /** Checks if the player has achieved the requirements to level up the Norma.
     *
     * The requirements should depend on the current goal.
     *
     * The player should call the normaCheck on a HomePanel. If the player trys to call normaCheck
     * from other type of panel the method should return false.
     *
     * For the time being, this implementation returns true if the level increase was achieved;
     * otherwise, it returns false.
     *
     * @param panel The panel where the player is located.
     *
     * @return True if the norma level has successfully increased.
     */
    def normaCheck(panel: Panel): Boolean
}
