package cl.uchile.dcc.citric
package model.unit.wildunit

import model.unit.AUnit

/** Class representing one of the available wild units
 *
 * @param name The name of the Wild Unit.
 */
class Seagull(override val name: String = "Seagull") extends AUnit(hp=3) with WildUnit {
    override val maxHp:   Int =  3
    override val attack:  Int =  1
    override val defense: Int = -1
    override val evasion: Int = -1
}