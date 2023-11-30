package cl.uchile.dcc.citric
package model.panel.concretePanel

import model.panel.APanel
import model.unit.player.IPlayer

/**A class representing a NeutralPanel.
 *
 * Does nothing.
 */
class NeutralPanel extends APanel {

    /** Literally, does nothing. */
    override def apply(player: IPlayer): Unit = return
}