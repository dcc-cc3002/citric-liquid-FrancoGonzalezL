package cl.uchile.dcc.citric
package exceptions

/** Custom exception to report that an invalid transition has been attempted.
 *
 *  This exception has been designed to provide more detail info about the invalid transition.
 *
 * @param details This message includes information about the current state.
 */
class InvalidTransitionException(details: String) extends Exception(s"An invalid transition was tried -- $details")
