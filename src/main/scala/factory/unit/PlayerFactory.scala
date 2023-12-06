package cl.uchile.dcc.citric
package factory.unit

import exceptions.FactoryConfigurationException
import model.unit.player.{IPlayer, PlayerCharacter}

class PlayerFactory extends AUnitFactory[IPlayer] {

    override def setRandomStats(): Unit = {
        super.setRandomStats()
        maxHp = Some(getRandomInt)
        attack = Some(getRandomInt)
        defense = Some(getRandomInt)
        evasion = Some(getRandomInt)
    }

    override def createUnit(name: String): IPlayer = {
        if(factoryIsReady)
            new PlayerCharacter(name, maxHp.get, attack.get, defense.get, evasion.get, random.get)
        else
            throw new FactoryConfigurationException("Player Factory is not ready: Some stat has not been set.")
    }

    override def factoryIsReady: Boolean = {
        super.factoryIsReady && maxHp.isDefined && attack.isDefined && defense.isDefined && evasion.isDefined
    }

    private var maxHp: Option[Int] = None
    private var attack: Option[Int] = None
    private var defense: Option[Int] = None
    private var evasion: Option[Int] = None
}
