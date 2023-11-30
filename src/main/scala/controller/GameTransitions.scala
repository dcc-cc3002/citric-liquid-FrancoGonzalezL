package cl.uchile.dcc.citric
package controller

/** Represent the all the possible transitions of the game. */
trait GameTransitions {

    /** startGame is called to initialize the game state and transition
     *  from the PreGame state to the StartGame state.
     */
    def startGame(debug: Boolean = false): Unit

    /** finishGame is called when the game has come to an end. */
    def finishGame(): Unit

    /** recoverPlayer is called during the Chapter phase when a player
     *  has to recover before playing again.
     */
    def recoverPlayer(): Unit

    /** play is called to commence a player's turn. */
    def playTurn(): Unit

    /** nextTurn is called to transition the game to the next player's turn. */
    def nextTurn(): Unit

    /** When a player has no more movements left or if the player has the options to stop.*/
    def stop(): Unit

    /** finishCombat should be called when the Player has won or lost the battle. */
    def finishCombat(): Unit

    /** encounterPanelEffect is called when a player lands on a EncounterPanel,
     *  this should trigger an battle between the player and a Wild Unit.
     */
    def encounterPanel(): Unit

    /** playAgain is called when the current game ends and there is an option
     *  to start over or play another round.
     */
    def playAgain(): Unit
}
