package cl.uchile.dcc.citric
package model.norma.levels

import model.norma.{ANorma, INorma}
import model.unit.player.IPlayer



/** Class representing a Norma level 4.
 *
 * @param player The player associated with the Norma.
 */
class NormaLvl4(player: IPlayer) extends ANorma(player) {
    override def normaLvl: Int = 4

    override protected def starsRequired: Int = 120

    override protected def victoriesRequired: Int = 10

    override protected def nextNormaLvl: INorma = new NormaLvl5(player)
}