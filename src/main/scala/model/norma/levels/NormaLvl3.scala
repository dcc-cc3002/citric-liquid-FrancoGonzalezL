package cl.uchile.dcc.citric
package model.norma.levels

import model.norma.{ANorma, INorma}
import model.unit.player.IPlayer


/** Class representing a Norma level 3.
 *
 * @param player The player associated with the Norma.
 */
class NormaLvl3(player: IPlayer) extends ANorma(player) {
    override def normaLvl: Int = 3

    override protected def starsRequired: Int = 70

    override protected def victoriesRequired: Int = 6

    override protected def nextNormaLvl: INorma = new NormaLvl4(player)
}
