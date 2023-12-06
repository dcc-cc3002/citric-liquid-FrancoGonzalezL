package cl.uchile.dcc.citric
package model.unit.wildUnit

import model.unit.IUnit

/** Represents a Wild Unit.
 *
 * The wild units should return a bonus of stars when they are defeated.
 */
trait IWildUnit extends IUnit {

    /** The bonus of stars of the wildUnit. */
    def bonusStars: Int
}
