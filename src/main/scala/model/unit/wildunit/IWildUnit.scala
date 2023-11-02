package cl.uchile.dcc.citric
package model.unit.wildunit

import model.unit.IUnit

trait IWildUnit extends IUnit {

    /** The bonus of stars of the wildUnit. */
    def bonusStars: Int
}
