package cl.uchile.dcc.citric
package controller.states.combat

import controller.GameController
import controller.states.{GameState, LandingPanel}

import cl.uchile.dcc.citric.model.panel.Panel
import cl.uchile.dcc.citric.model.unit.IUnit
import cl.uchile.dcc.citric.model.unit.player.IPlayer


/** Represent a Combat between a Player and another Player.
 *
 *  The combats will continue until one of the player is defeated.
 */
class CombatPvsP(controller: GameController) extends GameState(controller){

    override def play(): Unit = {
        val numberOfPlayers: Int = controller.currentPanel.charactersCount
        val panel: Panel = controller.currentPanel
        val init: Int = 2
        var playerSelectionMsg: String =
            s"""${controller.currentCharacter.name}
               |Select a player to attack:
               |1 -> Continue """.stripMargin

        playerSelectionMsg += panel.charactersToString(init)
        val selection = controller.receiveInput(playerSelectionMsg, numberOfPlayers)

        if(selection == 1)
            finishGame()
        else
            battle(selection - init)
    }

    override def finishCombat(): Unit = {
        this.changeState(new LandingPanel(controller))
    }

    private def battle(selection: Int): Unit = {
        val player1: IPlayer = controller.currentCharacter
        val player2: IPlayer = controller.currentPanel.getCharacterByIndex(selection)

        while(!(player1.isKO || player2.isKO)){
            attack(player1, player2)
            attack(player1, player1)
        }

        if(player1.isKO) player1.defeated(player2)
        else             player2.defeated(player1)
    }

    private def attack(attacker: IUnit, defender: IUnit): Unit = {
        val attack: Int = attacker.attack(defender)
        val msgAttacker: String = s"${attacker.name} is attacking ${defender.name} with ${attack} points of attack."
        controller.sendMsg(msgAttacker)

        val msgDefenderSelection: String =
            s"""${defender.name}
               |Select defense method:
               |1 -> Defend
               |2 -> Evade """.stripMargin
        val numberOfOptions: Int = 2
        val selection: Int = controller.receiveInput(msgDefenderSelection, numberOfOptions)

        defender.receiveAttack(attack, selection)
        val msgDefender: String = s"${defender.name}: hp = ${defender.hp}"
        controller.sendMsg(msgDefender)
    }
}