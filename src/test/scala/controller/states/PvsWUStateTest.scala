package cl.uchile.dcc.citric
package controller.states

import factory.unit.{PlayerFactory, UnitFactory, WildUnitFactory}
import model.unit.player.IPlayer

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.controller.states.combat.CombatPvsWU
import cl.uchile.dcc.citric.model.panel.Panel
import cl.uchile.dcc.citric.model.panel.concretePanel.EncounterPanel
import cl.uchile.dcc.citric.model.unit.IUnit
import cl.uchile.dcc.citric.model.unit.wildUnit.IWildUnit
import cl.uchile.dcc.citric.view.NullView

class PvsWU extends munit.FunSuite {
    val playerFactory: UnitFactory[IPlayer] = new PlayerFactory

    var game: GameController = _
    var player: IPlayer = _
    val panel: Panel = new EncounterPanel

    override def beforeEach(context: BeforeEach): Unit = {
        playerFactory.setRandomStats()
        player = playerFactory.createUnit("pablo")

        game = new GameController
        game.setView(new NullView)
        game.setState(new CombatPvsWU(game))
        player.moveToPanel(panel)
        game.addCharacter(player)
    }

    test("The battle should continue until one of the unit is defeated."){
        val unit: IUnit = panel.wildUnit.get
        game.play()

        assert(unit.hp == 0 || player.isKO)
    }


}
