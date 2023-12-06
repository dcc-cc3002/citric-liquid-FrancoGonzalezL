package cl.uchile.dcc.citric
package controller.states


import factory.unit.{PlayerFactory, UnitFactory, WildUnitFactory}
import model.unit.player.IPlayer

import cl.uchile.dcc.citric.controller.GameController
import cl.uchile.dcc.citric.controller.states.combat.CombatPvsWU
import cl.uchile.dcc.citric.controller.states.playerTurn.Recovery
import cl.uchile.dcc.citric.model.panel.Panel
import cl.uchile.dcc.citric.model.panel.concretePanel.EncounterPanel
import cl.uchile.dcc.citric.model.unit.IUnit
import cl.uchile.dcc.citric.model.unit.wildUnit.IWildUnit
import cl.uchile.dcc.citric.view.NullView

class RecoveryStateTest extends munit.FunSuite {
    val playerFactory: UnitFactory[IPlayer] = new PlayerFactory

    var game: GameController = _
    var player: IPlayer = _
    val panel: Panel = new EncounterPanel

    override def beforeEach(context: BeforeEach): Unit = {
        playerFactory.setRandomStats()
        player = playerFactory.createUnit("pablo")

        game = new GameController
        game.setView(new NullView)
        game.setState(new Recovery(game))

        player.moveToPanel(panel)
        game.addCharacter(player)
        player.hp = 0
    }

    test("A player should can only play if has recovered."){
        val turn: Int = game.turn
        game.play()
        /* The player recovers or the game moves to the next turn. */
        assert(!player.isKO || game.turn == turn + 1)
    }

}
