package cl.uchile.dcc.citric
package model.unit.wildunit

/** Class representing one of the available wild units */
class Seagull(override val name: String = "Seagull") extends WildUnit(3){
    override val maxHp:   Int =  3
    override val attack:  Int =  1
    override val defense: Int = -1
    override val evasion: Int = -1
}