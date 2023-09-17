package cl.uchile.dcc.citric
package model

/**A class representing a Drop Panel
 *
 * This panel will take away stars from the player.
 *
 * When landing on one, the player must roll a 6-sided die and will
 * lose a number of stars equal to roll*norma.
 */
class DropPanel extends APanel {
    /**Calculates the number of starts to remove from the player
     * and removes them.
     *
     * @param player The player that has moved to this Panel.
     */
    override def effect(player: PlayerCharacter): Unit = {

    }
}