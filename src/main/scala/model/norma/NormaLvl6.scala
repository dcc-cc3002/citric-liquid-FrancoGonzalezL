package cl.uchile.dcc.citric
package model.norma

import model.unit.player.IPlayer


/** Class representing a Norma level 6.
 *
 * @param player The player associated with the Norma.
 */
class NormaLvl6(player: IPlayer) extends ANorma(player) {

    override def normaLvl: Int = 6

    override protected def starsRequired: Int = 200

    override protected def victoriesRequired: Int = 14

    override protected def nextNormaLvl: INorma = this
}
