package cl.uchile.dcc.citric
package view

import view.msg.concrete.Msg.StringMsg

import cl.uchile.dcc.citric.view.msg.Displayable

class ViewTest extends munit.FunSuite {

    test("The Null View should return a random number between the min and the max."){
        val nullView: IView = new NullView
        val msg: Displayable = new StringMsg("input: ")

        val input: Int = nullView.receiveIntInput(msg)

        assert(msg.min <= input && input <= msg.max)
    }

    test("The Null View should return a random string of size 5."){
        val nullView: IView = new NullView
        val msg: Displayable = new StringMsg("input: ")

        val input: String = nullView.receiveStringInput(msg)

        assertEquals(input.length, 5)
        assertEquals(input.length, 5)
    }

}
