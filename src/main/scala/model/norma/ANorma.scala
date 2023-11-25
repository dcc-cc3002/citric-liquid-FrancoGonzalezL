package cl.uchile.dcc.citric
package model.norma

import model.unit.player.IPlayer
import model.panel.{Panel, HomePanel}
import model.norma.goals.{IGoal, StarsGoal, VictoriesGoal}

/** An abstract class representing a Norma.
 *
 * @param player The player associated with the Norma.
 */
abstract class ANorma(player: IPlayer) extends INorma{

    private val victories_s: String = "Victories"
    private val stars_s: String = "Stars"

    /** Current goal of the player.
     *
     * It needs to be initialized.
     */
    private var _goal: Option[IGoal] = None

    override def goal: String = {
        if (_goal.isEmpty) ""
        else _goal.get.toString
    }

    override def goal_=(option: String): Boolean = {
        if (_goal.isDefined) false
        else if(option==victories_s) {
            _goal = Some(new VictoriesGoal(victoriesRequired, player))
            true
        }else if(option==stars_s){
            _goal = Some(new StarsGoal(starsRequired, player))
            true
        }else
            false
    }

    override def normaCheck(panel: Panel): Option[INorma] = {
        if (_goal.isEmpty) None
        else if(_goal.get.achievedRequirements){
            Some(nextNormaLvl)
        }else
            None
    }
}
