package cl.uchile.dcc.citric
package model.panel

import model.unit.player.IPlayer

import scala.collection.mutable.ArrayBuffer

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

    def nextPanels: ArrayBuffer[Panel]

    def characters: ArrayBuffer[IPlayer]

    /** Returns True if a player is on the Panel. */
    def containsCharacter(character: IPlayer): Boolean

    /** Returns the current amount of players on the Panel. */
    def charactersCount: Int

    /** Returns True if otherPanel is on the nextPanels list. */
    def containsNextPanel(otherPanel: Panel): Boolean

    /** Returns the current amount of nextPanels on this Panel. */
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

    /** Removes a nextPanel from the list of nextPanels on this panel.
     *
     * otherPanel can only be removed if it's on the list.
     *
     * @param otherPanel The nextPanel to remove from this panel.
     */
    def removeNextPanel(otherPanel: Panel): Boolean

    def getNextPanelByIndex(index: Int): Panel

    def getCharacterByIndex(index: Int): IPlayer

    def nextPanelsToString(init: Int): String

    def charactersToString(init: Int): String

    /** The effect of the Panel in a player.
     *
     * This might be invoked when a player moves to this panel.
     *
     * Different types of Panels have different effects.
     *
     * @param player The player that has moved to this Panel.
     */
    def apply(player: IPlayer): Unit

    def canStopHere(player: IPlayer): Boolean
}

