package cl.uchile.dcc.citric

import controller.GameController

import scala.util.Random

object Main {
    def main(args: Array[String]): Unit = {
        val game: GameController = new GameController

        game.startGame()
        game.run()
    }
}
