package cl.uchile.dcc.citric
package view.msg

/** This represent all the objects that can be displayed to the user.
 *
 *  All should have a method that transform the object into a string.
 */
trait Displayable {

    /** Returns the message that has to be displayed to the user. */
    def toString: String

    /** For Int inputs, this is the upper bound.  */
    def max: Int

    /** For Int inputs, this is the lower bound. */
    def min: Int
}
