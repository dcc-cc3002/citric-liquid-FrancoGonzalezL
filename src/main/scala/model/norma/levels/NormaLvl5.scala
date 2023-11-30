package cl.uchile.dcc.citric
package model.norma.levels

import model.norma.{ANorma, INorma}
import model.unit.player.IPlayer


/** Class representing a Norma level 5.
 *
 * @param player The player associated with the Norma.
 */
class NormaLvl5(player: IPlayer) extends ANorma(player) {

    override def normaLvl: Int = 5

    override protected def starsRequired: Int = 200

    override protected def victoriesRequired: Int = 14

    override protected def nextNormaLvl: INorma = new NormaLvl6(player)
}