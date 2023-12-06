package cl.uchile.dcc.citric
package model.norma

import model.unit.player.IPlayer
import model.panel.Panel
import model.norma.goals.{IGoal, StarsGoal, VictoriesGoal}
import cl.uchile.dcc.citric.model.panel.concretePanel.HomePanel

/** An abstract class representing a Norma.
 *
 * @param player The player associated with the Norma.
 */
abstract class ANorma(player: IPlayer) extends INorma{

    /* Returns the maximum Norm level achievable in the game. */
    override def maxNormaLvl: Int = 6

    override def goal: String = {
        if (_goal.isEmpty) ""
        else _goal.get.toString
    }

    override def goal_=(option: Int): Boolean = {
        if (_goal.isDefined){
            false
        }
        else if (option == victories_s) {
            _goal = Some(new VictoriesGoal(victoriesRequired, player))
            true
        }else if (option == stars_s){
            _goal = Some(new StarsGoal(starsRequired, player))
            true
        }else
            false
    }

    override def normaCheck(panel: HomePanel): Option[INorma] = {
        if (_goal.isEmpty) None
        else if(_goal.get.achievedRequirements){
            Some(nextNormaLvl)
        }else
            None
    }


    /** Current goal of the player.
     *
     * It needs to be initialized.
     */
    private var _goal: Option[IGoal] = None

    private val stars_s: Int = 1
    private val victories_s: Int = 2
}
