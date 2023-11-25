package cl.uchile.dcc.citric
package controller

/** Represent all the things that can be checked during the game. */
trait GameChecks {

    /** Should return true if the game is starting (PreGame state). */
    def isStarting: Boolean

    /** Should return true if the game has finished. */
    def hasFinished: Boolean
}
