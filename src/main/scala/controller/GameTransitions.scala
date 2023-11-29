package cl.uchile.dcc.citric
package controller

/** Represent the all the possible transitions of the game. */
trait GameTransitions {

    /** startGame is called to initialize the game state and transition
     *  from the PreGame state to the StartGame state.
     */
    def startGame(): Unit

    /** finishGame is called when the game has come to an end,
     *  should be called from a Chapter state.
     */
    def finishGame(): Unit

    /** nextChapter is called to advance the game from the current
     *  chapter to the next chapter.
     */
    def nextChapter(): Unit

    /** recoverPlayer is called during the Chapter phase when a player
     *  has to recover before playing again.
     */
    def recoverPlayer(): Unit

    /** requirementsAchieved is called when a player meets the necessary
     *  conditions to proceed with its turn.
     */
    def requirementsAchieved(): Unit

    /** requirementsNotAchieved is called when a player fails to meet the necessary
     * conditions to proceed, triggering an alternative flow.
     */
    def requirementsNotAchieved(): Unit

    /** play is called to commence a player's turn.
     */
    def playTurn(): Unit

    /** rollDice is called when a player's turn involves rolling dice to determine
     *  the outcome of an action or movement.
     */
    def rollDice(): Unit

    /** choosePath is called when a player has to choose a path to follow after
     *  moving on the game board.
     */
    def choosePath(): Unit

    /** stop is called when a player's movement on the board comes to an end,
     *  usually when there are no more movements left.
     */
    def stop(): Unit

    /** noMovementsLeft is called when a player has no remaining movements,
     *  which may trigger certain panel effects or other game mechanics.
     */
    def noMovementsLeft(): Unit

    /** pass is called when a player decides to pass on an action or when
     *  no actions are available.
     */
    def pass(): Unit

    /** choosePlayer is called when a player needs to select another player,
     *  possibly for a PvP encounter or other interaction.
     */
    def choosePlayer(): Unit

    /** finishCombat should be called when the Player has won or lost the battle.
     */
    def finishCombat(): Unit

    /** encounterPanelEffect is called when a player lands on a EncounterPanel,
     *  this should trigger an battle between the player and a Wild Unit.
     */
    def encounterPanelEffect(): Unit

    /** nextTurn is called to transition the game to the next player's turn.
     */
    def nextTurn(): Unit

    /** playAgain is called when the current game ends and there is an option
     *  to start over or play another round.
     */
    def playAgain(): Unit
}
