package cl.uchile.dcc.citric
package model.unit.wildunit

class RoboBall(override val name: String = "RoboBall") extends WildUnit(3){
    override val maxHp: Int =  3
    override val attack: Int = -1
    override val defense: Int =  1
    override val evasion: Int = -1
}