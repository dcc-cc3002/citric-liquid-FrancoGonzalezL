package cl.uchile.dcc.citric
package model.unit
package wildunit

import util.Random

/** Class representing one of the three available 'bellacos'.
 *
 * @constructor Creates a Wild Unit with default values.
 *
 * @param name The name of the wild unit. The predetermined
 *             name is the name of the class.
 */
class RoboBall(
              override val name: String = "RoboBall",
              RandomNumberGenerator: Random = new Random()
              ) extends WildUnit(hpInitVal=3, RandomNumberGenerator) {
    override val maxHp:   Int =  3
    override val attack:  Int = -1
    override val defense: Int =  1
    override val evasion: Int = -1
}