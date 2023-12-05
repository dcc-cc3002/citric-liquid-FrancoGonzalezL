package cl.uchile.dcc.citric
package factory.unit

import exceptions.FactoryConfigurationException
import model.unit.wildUnit.IWildUnit
import model.unit.wildUnit.concreteWildUnit.{Chicken, RoboBall, Seagull}

class WildUnitFactory extends AUnitFactory[IWildUnit] {

    override def setRandomStats(): Unit = {
        super.setRandomStats()
        typeWildUnit = randomCreator.nextInt(3)
    }

    def createUnit(name: String): IWildUnit = {
        if (factoryIsReady)
            typeWildUnit match{
                case 0 =>
                    new Chicken(s"${classOf[Chicken].getSimpleName} $name", random.get)
                case 1 =>
                    new Seagull(s"${classOf[Seagull].getSimpleName} $name", random.get)
                case 2 =>
                    new RoboBall(s"${classOf[RoboBall].getSimpleName} $name", random.get)
            }
        else
            throw new FactoryConfigurationException("Player Factory is not ready: Some stat has not been set.")
    }

    var typeWildUnit: Int = 0
}
