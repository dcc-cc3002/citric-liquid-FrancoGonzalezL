package cl.uchile.dcc.citric
package view.msg

/** This represent the abstract of a Displayable object. */
abstract class ADisplayable extends Displayable {

    /** All the inputs should be greater at least 1. */
    override def min: Int = 1
}
