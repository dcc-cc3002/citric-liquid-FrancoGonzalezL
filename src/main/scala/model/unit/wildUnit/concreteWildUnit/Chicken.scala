package cl.uchile.dcc.citric
package model.unit.wildUnit.concreteWildUnit

import model.unit.wildUnit.AWildUnit

import scala.util.Random

/** Class representing one of the three available 'bellacos'.
 *
 * @constructor Creates a Wild Unit with default values.
 *
 * @param name The name of the wild unit. The predetermined
 *             name is the name of the class.
 */
class Chicken(
             override val name: String = "Chicken",
             RandomNumberGenerator: Random = new Random()
             ) extends AWildUnit(3, RandomNumberGenerator) {
    override val maxHp:   Int =  3
    override val attack:  Int = -1
    override val defense: Int = -1
    override val evasion: Int =  1

    override def bonusStars: Int = 3
}
