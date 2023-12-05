package cl.uchile.dcc.citric
package factory.unit

trait UnitFactory[+T] {
    def setRandomStats(): Unit

    def createUnit(name: String): T

    def factoryIsReady: Boolean

    def setMinMax(min: Int, max: Int): Unit
}
