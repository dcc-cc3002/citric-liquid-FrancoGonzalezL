package cl.uchile.dcc.citric
package model.unit.wildunit

/** Class representing one of the three available 'bellacos'.
 *
 * @constructor Creates a Wild Unit with default values.
 *
 * @param name The name of the wild unit. The predetermined
 *             name is the name of the class.
 */
class Chicken(override val name: String = "Chicken") extends WildUnit(hp=3) {
    override val maxHp:   Int =  3
    override val attack:  Int = -1
    override val defense: Int = -1
    override val evasion: Int =  1
}
