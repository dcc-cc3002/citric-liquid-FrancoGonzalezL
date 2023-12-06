package cl.uchile.dcc.citric
package controller.states.combat

import controller.states.GameState
import controller.GameController
import model.unit.IUnit
import model.unit.player.IPlayer
import view.msg.{DefenderSelectionMsg, StringMsg}

/** This Abstract state simulates the battle between two units.
 *
 *  All the States that have a battle will call this methods to simulate the battle.
 *
 * @param controller The Game Controller.
 */
abstract class ACombat(controller: GameController) extends GameState(controller) {

    /** This simulates the entire battle and ensures that the winners will receive their reward.
     *
     * Just one unit should be the winner.
     *
     * In a battle between unit1 and unit2, it does not matter if unit1 has been defeated
     * and unit1.attack(unit2) its called because a unit that has been defeated should not be able
     * to call the attack method.
     */
    protected def battle(defender: IUnit): Unit = {
        val player: IPlayer = controller.currentCharacter
        while ( !(player.isKO || defender.hp == 0) ) {
            attack(player, defender)
            if(defender.hp > 0)
                attack(defender, player)
        }
        if (player.isKO) {
            controller.view.sendMsg(new StringMsg(s"${player.name} has been defeated by ${defender.name}"))
            player.defeated(defender)
        } else {
            controller.view.sendMsg(new StringMsg(s"${defender.name} has been defeated by ${player.name}"))
            defender.defeated(player)
        }
    }

    /* Implementation of one step of the battle.
    *
    * This calculates the attack of the attacker and ensures if the defender can receive an input.
    *
    * If the defender can not receive an input, a random input will be send as a method of defense
    * for the defender.
    * */
    private def attack(attacker: IUnit, defender: IUnit): Unit = {
        val attack: Int = attacker.attack(defender)

        val msgAttacker: String = s"${attacker.name} is attacking ${defender.name} with ${attack} points of attack."
        controller.view.sendMsg(new StringMsg(msgAttacker))

        var selection: Int = 1
        if (defender.canReceiveInput) {
            selection = controller.view.receiveIntInput(new DefenderSelectionMsg(defender))
        } else {
            selection = (defender.rollDice() % 2) + 1
        }

        defender.receiveAttack(attack, selection)
        val msgDefender: String = s"${defender.name}: hp = ${defender.hp}"
        controller.view.sendMsg(new StringMsg(msgDefender))
    }
}
