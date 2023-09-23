package cl.uchile.dcc.citric
package model.panel

import model.unit.player.IPlayer

/**A class representing a NeutralPanel.
 *
 * Does nothing.
 */
class NeutralPanel extends APanel {
    /** Literally, does nothing. */
    override def effect(player: IPlayer): Unit = return
}