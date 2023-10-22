package cl.uchile.dcc.citric
package model.norma

import model.unit.player.IPlayer


/** Class representing a Norma level 2.
 *
 * @param player The player associated with the Norma.
 */
class NormaLvl2(player: IPlayer) extends ANorma(player) {

    override def normaLvl: Int = 2

    override def starsRequired: Int = 30

    override def victoriesRequired: Int = 3

    override def nextNormaLvl: INorma = new NormaLvl3(player)
}
