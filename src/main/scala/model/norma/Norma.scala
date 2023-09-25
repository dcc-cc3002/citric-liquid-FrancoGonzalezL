package cl.uchile.dcc.citric
package model.norma

import model.unit.player.IPlayer
import model.panel.{Panel, HomePanel}

/** A class representing a Norma.
 *
 * Each instance of Norma should be associated with a player.
 *
 * @constructor Creates a Norma with norma level equals 1. The goal is
 *              initialized empty, so it should be initialized.
 *
 * @param player The player associated with the Norma.
 */
class Norma(player: IPlayer) extends INorma{
    /** Victories String */
    private val victories_s = "Victories"
    /** Stars String */
    private val stars_s = "Stars"

    /** Goals and requirements to level up Norma. */
    private val goalOptions: Map[Int, Map[String, Int]] = Map(
        2 -> Map(stars_s -> 10 ,victories_s -> 1),
        3 -> Map(stars_s -> 30 ,victories_s -> 3),
        4 -> Map(stars_s -> 70 ,victories_s -> 6),
        5 -> Map(stars_s -> 120,victories_s -> 10),
        6 -> Map(stars_s -> 200,victories_s -> 14)
    )

    /** Current Norma of the player.
     *
     * Every player stars with Norma equals 1.
     */
    private var _norma: Int = 1

    /** Current goal of the player.
     *
     * It needs to be initialized.
     */
    private var _goal: Option[String] = None

    override def norma: Int = _norma

    override def goal: String = {
        if(_goal.isEmpty) ""
        else _goal.get
    }

    override def goal_=(option: String): Boolean = {
        if (_goal.isEmpty && (option == stars_s || option == victories_s)) {
            _goal = Some(option)
            true
        }else false
    }

    override def normaCheck(panel: Panel): Boolean = {
        if (_goal.isEmpty || norma == 6 || !panel.isInstanceOf[HomePanel]) false
        // If the player meets the requirements of the goal.
        else if((goal == stars_s     && player.stars     >= goalOptions(norma+1)(stars_s)) ||
                (goal == victories_s && player.victories >= goalOptions(norma+1)(victories_s)) ){
            // Level up Norma.
            _norma += 1
            _goal = None
            true
        }else false
    }
}
