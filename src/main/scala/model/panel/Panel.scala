package cl.uchile.dcc.citric
package model.panel

import model.unit.player.IPlayer

/** Represents a single cell on a board, known as a Panel.
 *
 * Each panel has its own effect, which can be applied to a character.
 * In the context of the board game, a panel represents a tile or space that a character lands on
 * and experiences an effect (e.g., losing stars, fighting an enemy, etc.).
 * Panels can also be connected to other panels, allowing for the formation of complex board
 * structures.
 *
 * @author [[https://github.com/r8vnhill Ignacio Slater M.]]
 * @author [[https://github.com/FrancoGonzalezL Franco González L.]]
 */
trait Panel {
    /** Returns the type of the Panel.
     *
     * The type of the Panel is the name of the class.
     */
    def panelType: String

    def containsCharacter(character: IPlayer): Boolean

    def charactersCount: Int

    def isPrevTo(otherPanel: Panel): Boolean

    def nextPanelsCount: Int

    /** Adds a character to the list of characters currently on this panel.
     *
     * This might be invoked when a player moves to this panel or starts their turn on it.
     *
     * @param player The player character to add to this panel.
     */
    def addCharacter(player: IPlayer): Boolean

    /** Removes a character from the list of characters currently on this panel.
     *
     * This might be invoked when a player moves off this panel.
     *
     * @param player The player character to remove from this panel.
     */
    def removeCharacter(player: IPlayer): Boolean

    /**Adds a Panel to the list of nextPanels.
     *
     * This might be use to connect the panels
     *
     * @param otherPanel    The panel to link.
     *
     * @return True if the panel has been added correctly;
     *         otherwise false.
     */
    def addNextPanel(otherPanel: Panel): Boolean

    /**The effect of the Panel in a player.
     *
     * This might be invoked when a player moves to this panel.
     *
     * Different types of Panels have different effects.
     *
     * @param player The player that has moved to this Panel.
     */
    def apply(player: IPlayer): Boolean
}

