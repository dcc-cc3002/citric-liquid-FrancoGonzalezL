package cl.uchile.dcc.citric
package model.unit

/** Abstract class representing a Unit.
 *
 * @param _hp The current hp of the player. In order to initialize the hp
 *            equals to the maxHp, it must be received as a parameter.
 */
abstract class AUnit(   /** Current amount of hit points */
                        private var _hp: Int
                    ) extends IUnit {

    /** Current amount of stars */
    private var _stars: Int = 0

    override def stars: Int = _stars

    override def addStars(amount: Int): Unit = {
        if (amount >= 0)
            _stars = stars + amount
    }

    override def removeStars(amount: Int): Unit = {
        if (amount >= 0)
            _stars = math.max(0, stars-amount)
    }

    override def hp: Int = _hp

    override def increaseHp(amount: Int): Unit = {
        if(amount >= 0)
            _hp = math.min(maxHp, hp+amount)
    }

    override def reduceHp(amount: Int): Unit = {
        if(amount >= 0)
            _hp = math.max(0, hp-amount)
    }
}
