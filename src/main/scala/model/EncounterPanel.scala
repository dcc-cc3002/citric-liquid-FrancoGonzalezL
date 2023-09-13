package cl.uchile.dcc.citric
package model

/**A class representing an Encounter Panel.
 *
 * When landing on one, the player will enter into battle with a random Wild Unit.
 */
class EncounterPanel extends APanel {
    /**Generates a random Wild Unit and calculates the fight between the player and the unit.
     *
     * @param player The player that has moved to this Panel.
     */
    override def effect(player: PlayerCharacter): Unit = return
}