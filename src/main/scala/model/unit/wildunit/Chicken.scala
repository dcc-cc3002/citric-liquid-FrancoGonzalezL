package cl.uchile.dcc.citric
package model.unit.wildunit

class Chicken(override val name: String = "Chicken") extends WildUnit(3) {
    override val maxHp:   Int =  3
    override val attack:  Int = -1
    override val defense: Int = -1
    override val evasion: Int =  1
}
