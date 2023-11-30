package cl.uchile.dcc.citric
package model.panel.home

import model.panel.APanel
import model.unit.player.IPlayer

/**A class representing a Home Panel.
 *
 * @param _owner The player that owns the Panel
 */
class HomePanel(_owner: IPlayer) extends APanel with Home {

    override def owner: IPlayer = _owner

    /** Gives the player 1 point of life and performs a Norma Check
     *
     * @param player The player that has moved to this Panel.
     */
    override def apply(player: IPlayer): Unit = {
        if (this.containsCharacter(player)) {
            player.hp += 1
            player.normaCheck(panel=this)
        }
    }

    override def canStopHere(player: IPlayer): Boolean = {
        if(player == owner) true
        else false
    }
}
