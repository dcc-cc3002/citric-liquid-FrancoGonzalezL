package cl.uchile.dcc.citric
package model.unit

abstract class AUnit(
                        /** Current amount of hit points */
                        private var hp: Int
                    ) extends IUnit {

    /** Current amount of stars */
    private var stars: Int = 0

    override def getStars: Int = stars

    override def addStars(amount: Int): Unit = {
        if (amount >= 0)
            stars += amount
    }

    override def removeStars(amount: Int): Unit = {
        if (amount < 0) return
        if (stars >= amount)
            stars -= amount
        else
            stars = 0
    }

    override def getHp: Int = hp

    override def increaseHp(amount: Int): Unit = {
        if(amount < 0) return
        hp += amount
        if(hp > maxHp)
            hp = maxHp
    }

    override def reduceHp(amount: Int): Unit = {
        if(amount <= 0) return
        hp -= amount
        if(hp < 0)
            hp = 0
    }
}
