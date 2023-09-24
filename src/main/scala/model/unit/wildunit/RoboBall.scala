package cl.uchile.dcc.citric
package model.unit.wildunit

/** Class representing one of the available wild units
 *
 * @param name The name of the Wild Unit.
 */
class RoboBall(override val name: String = "RoboBall") extends WildUnit(3){
    override val maxHp: Int =  3
    override val attack: Int = -1
    override val defense: Int =  1
    override val evasion: Int = -1
}