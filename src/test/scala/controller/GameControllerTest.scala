package cl.uchile.dcc.citric
package controller

import exceptions.InvalidTransitionException
import controller.states.Chapter

class IGameControllerTest extends munit.FunSuite {
    var game: GameController = _

    override def beforeEach(context: BeforeEach): Unit = {
        game = new GameController
    }

    test("Valid transitions from PreGame"){
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.startGame()
        )
        validTransitionsList.foreach(f => {
            try {
                f(game)
            } catch {
                case e: InvalidTransitionException =>
                    throw new AssertionError(
                        s"An unexpected exception was thrown: ${e.getMessage}"
                    )
            }
        })
    }

    test("Invalid transitions from PreGame"){
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.finishGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.playTurn(),
            (g: GameController) => g.stop(),
            (g: GameController) => g.finishCombat(),
            (g: GameController) => g.nextTurn(),
            (g: GameController) => g.playAgain()
        )

        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            interceptMessage[InvalidTransitionException](
                "An invalid transition was tried -- From PreGame"
            ) {
                f(game)
            }
        })

    }

    test("Valid transitions from Chapter") {
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.finishGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.playTurn(),
        )
        validTransitionsList.foreach(f => {
            try {
                game.setState(new Chapter(game))
                f(game)
            } catch {
                case e: InvalidTransitionException =>
                    throw new AssertionError(
                        s"An unexpected exception was thrown: ${e.getMessage}"
                    )
            }
        })
    }

    test("Invalid transitions from Chapter") {
        game.startGame()
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.startGame(),
            (g: GameController) => g.stop(),
            (g: GameController) => g.finishCombat(),
            (g: GameController) => g.nextTurn(),
            (g: GameController) => g.playAgain()
        )

        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            interceptMessage[InvalidTransitionException](
                "An invalid transition was tried -- From Chapter"
            ) {
                f(game)
            }
        })

    }
}

