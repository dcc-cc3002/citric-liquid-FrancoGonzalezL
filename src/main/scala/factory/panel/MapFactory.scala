package cl.uchile.dcc.citric
package factory.panel

import model.panel.Panel

import model.unit.player.IPlayer

trait MapFactory {

    def createMap(): Unit

    def setPlayers(players: Array[IPlayer]): Unit
}
