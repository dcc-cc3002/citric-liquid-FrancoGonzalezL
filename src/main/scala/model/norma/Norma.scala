package cl.uchile.dcc.citric
package model.norma

import model.unit.player.IPlayer

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
    private var norma: Int = 1

    /** Current goal of the player.
     *
     * It needs to be initialized.
     */
    private var goal: Option[String] = None

    override def getNorma: Int = norma

    override def setGoal(option: String): Boolean = {
        if (goal.isEmpty && (option == stars_s || option == victories_s)) {
            goal = Some(option)
            true
        }else false
    }

    override def normaCheck(): Boolean = {
        if (goal.isEmpty) return false
        if (norma == 6) return false
        // If the player meets the requirements of the goal.
        if (goal.get == stars_s && player.getStars >= goalOptions(norma + 1)(stars_s) ||
            goal.get == victories_s && player.getVictories >= goalOptions(norma + 1)(victories_s)) {
            // Level up Norma.
            norma += 1
            goal = None
            true
        }else false
    }
}
