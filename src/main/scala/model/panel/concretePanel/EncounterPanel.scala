package cl.uchile.dcc.citric
package model.panel.concretePanel

import model.panel.APanel
import model.unit.IUnit
import model.unit.player.IPlayer
import model.unit.wildUnit.concreteWildUnit.Chicken

import factory.unit.{UnitFactory, WildUnitFactory}
import model.unit.wildUnit.IWildUnit

/**A class representing an Encounter Panel.
 *
 * When landing on one, the player will enter into battle with a random Wild Unit.
 */
class EncounterPanel extends APanel {

    /** Generates a random Wild Unit.
     *
     * @param player The player that has moved to this Panel.
     */
    override def apply(player: IPlayer): Unit = wildUnit

    override def wildUnit: Option[IUnit] = {
        if(_wildUnit.isEmpty || _wildUnit.get.hp == 0) {
            wildUnitFactory.setRandomStats()
            _wildUnit = Some(wildUnitFactory.createUnit(""))
        }
        _wildUnit
    }

    /** Current wildUnit on the Panel */
    private var _wildUnit: Option[IUnit] = None
    /* Factory to generate the wild units */
    private val wildUnitFactory: UnitFactory[IWildUnit] = new WildUnitFactory
}