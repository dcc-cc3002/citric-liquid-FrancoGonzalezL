package cl.uchile.dcc.citric
package model.norma.levels

import model.norma.{ANorma, INorma}
import model.unit.player.IPlayer


/** Class representing a Norma level 1.
 *
 * @param player The player associated with the Norma.
 */
class NormaLvl1(player: IPlayer) extends ANorma(player: IPlayer) {

    override def normaLvl: Int = 1

    override def starsRequired: Int = 10

    override def victoriesRequired: Int = 1

    override def nextNormaLvl: INorma = new NormaLvl2(player)
}