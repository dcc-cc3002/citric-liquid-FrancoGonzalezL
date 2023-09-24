package cl.uchile.dcc.citric
package model.unit
package wildunit

import model.unit.AUnit

/** Abstract class representing the Wild Units.
 *
 * @param hp The current hp of the wild unit. It should be received as
 *           a parameter to initialize the hp equals to the maxHp.
 */
abstract class WildUnit(hp: Int) extends AUnit(hp)
