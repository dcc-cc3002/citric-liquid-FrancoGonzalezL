package cl.uchile.dcc.citric
package factory.panel

import model.unit.player.IPlayer

/** Trait defining the blueprint for a MapFactory.
 *
 * A MapFactory is responsible for creating the game map and assigning players to it.
 */
trait MapFactory {

    /**
     * Creates the game map with all necessary components and settings.
     *
     * This should include the initialization of any panels, paths, and other elements that constitute the game board.
     */
    def createMap(): Unit

    /** Sets the players on the map, typically at the beginning of the game.
     *
     * @param players An array of IPlayer objects representing the players who will participate in the game.
     *                This array should be used to place players on the starting positions of the map and
     *                potentially distribute them according to the game's rules.
     */
    def setPlayers(players: Array[IPlayer]): Unit
}
