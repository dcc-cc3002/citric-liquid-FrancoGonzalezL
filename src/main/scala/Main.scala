package cl.uchile.dcc.citric

import controller.GameController

/** Simulates the Game. */
object Main {

    def main(args: Array[String]): Unit = {
        val game: GameController = new GameController

        game.startGame()
        game.run()
    }
}
