package cl.uchile.dcc.citric
package controller

import controller.states.startAndEnd.{EndGame, PreGame}
import controller.states.{Chapter, LandingPanel}
import exceptions.InvalidTransitionException
import view.NullView
import controller.states.combat.{CombatPvsP, CombatPvsWU}
import controller.states.playerTurn.{PlayerTurn, Recovery}
import org.junit.Assert

class TransitionsAndChecksTest extends munit.FunSuite {
    var game: GameController = _

    override def beforeEach(context: BeforeEach): Unit = {
        game = new GameController
        game.setView(new NullView)
    }

    test("We can know when the game is starting"){
        game.setState(new PreGame(game))
        assert(game.isStarting)
    }

    test("We can know if the game has ended."){
        game.setState(new EndGame(game))
        assert(game.hasFinished)
    }

    test("Valid transitions from PreGame") {
        game.setState(new PreGame(game))
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.startGame()
        )

        /* If a transitions fails it will throw a new Exception.
        *  If no exceptions is throw, all the transitions are valid.
        */
        validTransitionsList.foreach(f => f(game))
    }

    test("Invalid transitions from PreGame") {
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.finishGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.playTurn(),
            (g: GameController) => g.stop(),
            (g: GameController) => g.finishCombat(),
            (g: GameController) => g.nextTurn(),
            (g: GameController) => g.playAgain(),
            (g: GameController) => g.encounter()
        )
        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            game.setState(new PreGame(game))
            Assert.assertThrows(classOf[InvalidTransitionException], () => f(game))
        })
    }

    test("Valid transitions from Chapter") {
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.finishGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.playTurn()
        )
        validTransitionsList.foreach(f => {
            game.setState(new Chapter(game))
            f(game)
        })
    }

    test("Invalid transitions from Chapter") {
        game.setState(new Chapter(game))
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.startGame(),
            (g: GameController) => g.stop(),
            (g: GameController) => g.nextTurn(),
            (g: GameController) => g.playAgain(),
            (g: GameController) => g.finishCombat(),
            (g: GameController) => g.encounter()
        )
        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            Assert.assertThrows(classOf[InvalidTransitionException], () => f(game))
        })
    }

    test("Valid transitions from Player Turn") {
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.stop()
        )
        validTransitionsList.foreach(f => {
            game.setState(new PlayerTurn(game))
            f(game)
        })
    }

    test("Invalid transitions from Player Turn") {
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.startGame(),
            (g: GameController) => g.finishGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.playTurn(),
            (g: GameController) => g.finishCombat(),
            (g: GameController) => g.nextTurn(),
            (g: GameController) => g.playAgain(),
            (g: GameController) => g.encounter()
        )
        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            game.setState(new PlayerTurn(game))
            Assert.assertThrows(classOf[InvalidTransitionException], () => f(game))
        })
    }

    test("Valid transitions from Combat PvsP") {
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.finishCombat()
        )
        validTransitionsList.foreach(f => {
            game.setState(new CombatPvsP(game))
            f(game)
        })
    }

    test("Invalid transitions from Combat PvP") {
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.startGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.stop(),
            (g: GameController) => g.playTurn(),
            (g: GameController) => g.nextTurn(),
            (g: GameController) => g.playAgain(),
            (g: GameController) => g.encounter(),
            (g: GameController) => g.finishGame()
        )
        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            game.setState(new CombatPvsP(game))
            Assert.assertThrows(classOf[InvalidTransitionException], () => f(game))
        })
    }

    test("Valid transitions from Landing Panel"){
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.encounter()
        )
        validTransitionsList.foreach(f => {
            game.setState(new LandingPanel(game))
            f(game)
        })
    }

    test("Invalid transitions from Landing Panel") {
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.finishGame(),
            (g: GameController) => g.startGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.stop(),
            (g: GameController) => g.playTurn(),
            (g: GameController) => g.finishCombat(),
            (g: GameController) => g.nextTurn(),
            (g: GameController) => g.playAgain()
        )
        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            game.setState(new LandingPanel(game))
            Assert.assertThrows(classOf[InvalidTransitionException], () => f(game))
        })
    }

    test("Valid transitions from Combat PvWU") {
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.nextTurn()
        )
        validTransitionsList.foreach(f => {
            game.setState(new CombatPvsWU(game))
            f(game)
        })
    }

    test("Invalid transitions from Combat PvWU") {
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.finishGame(),
            (g: GameController) => g.startGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.stop(),
            (g: GameController) => g.playTurn(),
            (g: GameController) => g.finishCombat(),
            (g: GameController) => g.playAgain(),
            (g: GameController) => g.encounter()
        )
        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            game.setState(new CombatPvsWU(game))
            Assert.assertThrows(classOf[InvalidTransitionException], () => f(game))
        })
    }

    test("Valid transitions from End Game") {
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.playAgain()
        )
        validTransitionsList.foreach(f => {
            game.setState(new EndGame(game))
            f(game)
        })
    }

    test("Invalid transitions from End Game") {
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.finishGame(),
            (g: GameController) => g.startGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.stop(),
            (g: GameController) => g.playTurn(),
            (g: GameController) => g.finishCombat(),
            (g: GameController) => g.encounter(),
            (g: GameController) => g.nextTurn()
        )
        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            game.setState(new EndGame(game))
            Assert.assertThrows(classOf[InvalidTransitionException], () => f(game))
        })
    }

    test("Valid transitions from Recovery") {
        /* A list that contains all valid transitions */
        val validTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.playTurn(),
            (g: GameController) => g.nextTurn()
        )
        validTransitionsList.foreach(f => {
            game.setState(new Recovery(game))
            f(game)
        })
    }

    test("Invalid transitions from Recovery") {
        /* A list that contains all invalid transitions */
        val invalidTransitionsList: List[GameController => Unit] = List(
            (g: GameController) => g.finishGame(),
            (g: GameController) => g.startGame(),
            (g: GameController) => g.recoverPlayer(),
            (g: GameController) => g.stop(),
            (g: GameController) => g.finishCombat(),
            (g: GameController) => g.encounter(),
            (g: GameController) => g.playAgain()
        )
        /* Test all transitions */
        invalidTransitionsList.foreach(f => {
            game.setState(new Recovery(game))
            Assert.assertThrows(classOf[InvalidTransitionException], () => f(game))
        })
    }
}
