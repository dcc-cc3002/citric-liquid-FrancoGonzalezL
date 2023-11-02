package cl.uchile.dcc.citric
package model.unit

import player.IPlayer
import wildunit.IWildUnit

/** Represents the entities tha will participate int the game,
 * either as a character, controllable by the user, or as a
 * Wild Unit, not controllable by the user
 *
 * @author [[https://github.com/FrancoGonzalezL Franco González L.]]
 */
trait IUnit {

    /** The name of the unit */
    val name: String

    /** The max hit points of the unit */
    val maxHp: Int

    /** The attack points of the unit */
    val attack: Int

    /** The defence points of the unit */
    val defense: Int

    /** The evasion points of the unit */
    val evasion: Int

    /** Return the current amount of hit points */
    def hp: Int

    /** Method to get the current amount of stars. */
    def stars: Int

    /** Sets the stars.
     *
     * @param amount The new value of stars.
     */
    def stars_=(amount: Int): Unit

    /** Sets the hp
     *
     * @param amount The new value of the hp.
     */
    def hp_=(amount: Int): Unit

    /** This might be invoked when a unit enters an encounter with another unit and
     *  its the turn of the unit to attack.
     *
     * @param unit The unit that is being attacked.
     *
     * @return True if the attack defeats the other unit.
     */
    def attack(unit: IUnit): Boolean

    /** This might be invoked when a unit is being attacked.
     *
     *  If a player is being attacked, it should give the player the option
     *  to evade or defend.
     *
     *  If a unit is being attack, the unit should choose to evade or defend.
     *
     *  @return True if the unit under attack has been defeated.
     */
    def receiveAttack(attack: Int): Boolean

    /** This might be invoked when a player wants to defend an attack.
     *
     * The unit has to roll the dice and will lose an amount of hp equals to
     * max{1, attack - (roll + defense)}
     *
     * @param attack The value of (attack + roll) of the enemy.
     *
     * @return True if the unit was defeated by the attack.
     */
    def defend(attack: Int): Boolean

    /** This might be invoked when a player wants to evade an attack.
     *
     * The unit has to roll the dice and if (roll + evasion > attack ), then
     * the unit should not receive damage, in oder case the unit will receive all the damage.
     *
     * @param attack The value of (attack + roll) of the enemy.
     *
     * @return True if the unit was defeated by the attack.
     */
    def evade(attack: Int): Boolean

    /** Rolls a dice and returns a value between 1 to 6. */
    def rollDice(): Int

    /** This might be invoked when a unit has won a fight.
     *
     *  To call this method, the unit should have been defeated (0 hp).
     *

     *
     *  @return True if the unit has been defeated. False if the method
     *          has been called and the unit has hp > 0.
     */
    def defeated(attacker: IUnit): Boolean

    /** This might be invoked when a unit has won against a player.
     *
     *  - Adds 2 victories to the winner.
     *  - Gives half of the player's stars to the winner.    *
     *
     * @param defeated The player that has been defeated.
     *
     * @return True if the reward has been delivered successfully.
     */
    def getRewardFromPlayer(defeated: IPlayer): Boolean

    /** This might be invoked when a unit has won against a WildUnit.
     *
     *  - Adds 1 victory to the winner.
     *  - Gives all of the unit's stars to the winner, plus the bonus of the wildUnit.
     *
     * @param defeated The WildUnit that has been defeated.
     *
     * @return True if the reward has been delivered successfully.
     */
    def getRewardFromWildUnit(defeated: IWildUnit): Boolean
}
