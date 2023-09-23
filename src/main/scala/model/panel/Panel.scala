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
    /** Array of the characters currently positioned on this panel.
     *
     * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
     * land on the same space.
     */
    val characters: ArrayBuffer[IPlayer]

    /** An array of panels that are directly connected to this one.
     *
     * In the context of the game, multiple routes or paths may exist, this could represent the
     * possible next steps a player might take after being on this panel.
     *
     * @return a List of Panel instances that are adjacent or connected to this panel.
     */
    val nextPanels: ArrayBuffer[Panel]

    /** Returns the type of the Panel.
     *
     * The type of the Panel is the name of the class.
     */
    def panelType: String

    /** Adds a character to the list of characters currently on this panel.
     *
     * This might be invoked when a player moves to this panel or starts their turn on it.
     *
     * @param player The player character to add to this panel.
     */
    def addCharacter(player: IPlayer): Unit

    /** Removes a character from the list of characters currently on this panel.
     *
     * This might be invoked when a player moves off this panel.
     *
     * @param player The player character to remove from this panel.
     */
    def removeCharacter(player: IPlayer): Unit

    /**Adds a Panel to the list of nextPanels.
     *
     * This might be use to connect the panels
     *
     * @param otherPanel      The panel to link.
     * @param multi_link_mode Choose the method for linking the panels.
     * @example
     * {{{
     *     val a: Panel = new NeutralPanel
     *     val b: Panel = new NeutralPanel
     *     val c: Panel = new NeutralPanel
     *     val x: Panel = new NeutralPanel
     *
     *     a.addNextPanel(c, multi_link_mode = false)
     *     // ( a->c; c->a )
     *
     *     a.addNextPanel(b, multi_link_mode = false)
     *     // ( a->b; b->c; c->a )
     *
     *    a.addNextPanel(x, multi_link_mode = true)
     *     // ( a->x,b; x->a; b->c; c->a )
     * }}}
     */
    def addNextPanel(otherPanel: Panel, multi_link_mode: Boolean): Unit

    /**The effect of the Panel in a player.
     *
     * This might be invoked when a player moves to this panel.
     *
     * Different types of Panels have different effects.
     *
     * @param player The player that has moved to this Panel.
     */
    def effect(player: IPlayer): Unit
}

