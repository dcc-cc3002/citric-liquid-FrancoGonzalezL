package cl.uchile.dcc.citric
package model.panel.home

import model.unit.player.IPlayer
import model.panel.Panel

/** This represent a Home Panel.
 *
 * Should have an owner.
 * All players must have a Home.
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
 */
trait Home extends Panel {

    def owner: IPlayer
}
