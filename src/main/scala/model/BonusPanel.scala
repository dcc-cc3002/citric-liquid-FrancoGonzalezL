package cl.uchile.dcc.citric
package model

/**A class representing a Bonus Panel
 *
 * This panel grants stars to the player.
 *
 * When landing on one, the player must roll a 6-sided die and will
 * win a number of stars equal to min(roll*norma, roll*3)
 */
class BonusPanel extends APanel {
    /**Calculates the number of starts to give
     * and adds them to the player.
     *
     * @param player The player that has moved to this Panel.
     */
    override def effect(player: PlayerCharacter): Unit = return
}
