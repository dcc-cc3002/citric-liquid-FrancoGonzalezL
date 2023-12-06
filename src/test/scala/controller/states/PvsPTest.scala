package cl.uchile.dcc.citric
package controller.states

import factory.unit.{PlayerFactory, UnitFactory}
import controller.GameController
import controller.states.combat.CombatPvsP
import model.panel.Panel
import model.panel.concretePanel.EncounterPanel
import model.unit.IUnit
import model.unit.player.IPlayer
import view.NullView

class PvsPTest extends munit.FunSuite {
    val playerFactory: UnitFactory[IPlayer] = new PlayerFactory

    var game: GameController = _
    var player: IPlayer = _
    val panel: Panel = new EncounterPanel

    override def beforeEach(context: BeforeEach): Unit = {
        playerFactory.setRandomStats()
        player = playerFactory.createUnit("pablo")

        game = new GameController
        game.setView(new NullView)
        game.startGame()

        game.setState(new CombatPvsP(game))

        game.addCharacter(player)
    }

    test("The battle should continue until one of the unit is defeated.") {
        val unit: IUnit = panel.wildUnit.get
        game.play()

        assert(unit.hp == 0 || player.isKO)
    }


}
