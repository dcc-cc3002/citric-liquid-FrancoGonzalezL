package cl.uchile.dcc.citric
package model.panel

import model.unit.player.IPlayer

/**A class representing a Home Panel.
 *
 * This panel must have an owner.
 * All players must have a Home Panel.
 *
 * If the player is the owner of the Panel, they can choose to stop on
 * it when passing over it, even if they have remaining moves available.
 *
 * If the player is not the owner, the Panel will only activate if the player
 * lands exactly on it.
 *
 * In both cases, the turn will end after activating the effect of the Panel.
 * Upon activation, the player will regain 1 point of life, and the panel will perform
 * a Norma Check.
 *
 * @param owner The player that owns the Panel
 */
class HomePanel(val owner: IPlayer) extends APanel {

    /* Provisional method */
    /**Gives the player 1 point of life and performs a Norma Check
     *
     * @param player The player that has moved to this Panel.
     */
    override def effect(player: IPlayer): Unit = {
        player.increaseHp(1)
        player.normaCheck()
    }
}
