package cl.uchile.dcc.citric
package controller.states.combat

import controller.states.GameState
import controller.GameController
import model.unit.IUnit
import model.unit.player.IPlayer
import view.msg.{DefenderSelectionMsg, StringMsg}

abstract class ACombat(controller: GameController) extends GameState(controller) {

    protected def battle(defender: IUnit): Unit = {
        val player: IPlayer = controller.currentCharacter
        while ( !(player.isKO || defender.hp <= 0) ) {
            attack(player, defender)
            attack(player, defender)
        }
        if (player.isKO)
            player.defeated(defender)
        else
            defender.defeated(player)
    }

    private def attack(attacker: IUnit, defender: IUnit): Unit = {
        val attack: Int = attacker.attack(defender)

        val msgAttacker: String = s"${attacker.name} is attacking ${defender.name} with ${attack} points of attack."
        controller.view.sendMsg(new StringMsg(msgAttacker))

        var selection: Int = 1
        defender match {
            case d: IPlayer => selection = controller.view.receiveIntInput(new DefenderSelectionMsg(d))
            case d: IUnit   => selection = (d.rollDice() % 2) + 1
        }

        defender.receiveAttack(attack, selection)

        val msgDefender: String = s"${defender.name}: hp = ${defender.hp}"
        controller.view.sendMsg(new StringMsg(msgDefender))
    }

}
