package cl.uchile.dcc.citric
package model.unit
package player
import model.norma.INorma

/** Represents a Player.
 *
 * Players have a victories counter, it might be use to increase the norma level.
 *
 * Victories are obtained by winning battles, and the number of victories earned in combat
 * will depend on the type of enemy faced:
 *
 * 1 Victory if the unit defeated was a Wild Unit.
 *
 * 2 Victories if the unit defeated was other player.
 *
 * When a character is defeated and enters the K.O. state, they will enter a phase called Recovery,
 * during which the player cannot take their turn.
 *
 * To exit this phase, the player must roll a die and obtain a quantity greater than or equal to
 * the required amount for recovery. If they succeed in recovering, then they can immediately take their turn.
 */
trait IPlayer extends IUnit with INorma {

    /** Returns the current amount of victories */
    def victories: Int

    /** Adds victories to the player.
     *
     * The number of victories added depend on the enemy faced.
     *
     * @param unit The enemy.
     */
    def addVictories(unit: IUnit): Unit

    /** Returns true if the player has been knockout */
    def isKO: Boolean

    /**This might be invoked when it's the player's turn but the player has been knockout.
     *
     * @param required The required amount to recover, it starts
     *                 at 6 and decreases by 1 as the Chapters progress.
     */
    def recovery(required: Int): Unit

    /** Rolls a dice and returns a value between 1 to 6. */
    def rollDice(): Int
}
