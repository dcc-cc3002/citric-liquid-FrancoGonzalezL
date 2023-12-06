package cl.uchile.dcc.citric
package factory.unit

import scala.util.Random

abstract class AUnitFactory[T] extends UnitFactory[T] {

    def setRandomStats(): Unit = {
        random = Some(new Random(getRandomInt))
    }

    protected def getRandomInt: Int = {
        randomCreator.nextInt(_max - _min) + _min + 1
    }


    override def setMinMax(min: Int, max: Int): Unit = {
        if(min < 1 || max < 1 || max < min) return
        _min = min - 1
        _max = max
    }

    override def factoryIsReady: Boolean = {
        random.isDefined
    }

    protected var random: Option[Random] = None
    protected var randomCreator: Random = new Random(100)
    private var _min: Int = 2
    private var _max: Int = 6
}
