package cl.uchile.dcc.citric
package factory.panel
import model.panel.Panel
import model.unit.player.IPlayer
import exceptions.FactoryConfigurationException
import model.panel.concretePanel.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel}

class BasicMapFactory extends MapFactory {

    override def createMap(): Unit = {
        if (players.length < 1)
            throw new FactoryConfigurationException("Player has not been set.")

        var head: Option[Panel]    = None
        var current: Option[Panel] = None

        players.foreach(player => {
            for(_ <- 1 to (mapSize/players.length)){
                val newPanel: Panel = createPanel(player)
                if(current.isDefined) {
                    current.get.addNextPanel(newPanel)
                } else {
                    /* first panel created. */
                    head = Some(newPanel)
                    current = head
                }

                current = Some(newPanel)

                if(current.get.canStopHere(player))
                    player.moveToPanel(current.get)

                generateNextType()
            }
        })

        if(head.isDefined && !current.get.equals(head.get))
            current.get.addNextPanel(head.get)
    }

    override def setPlayers(players: Array[IPlayer]): Unit = {
        this.players = players
    }

    private def generateNextType(): Unit = {
        currentPanelType = (currentPanelType +  1) % 5
    }

    private def createPanel(player: IPlayer): Panel = {

        if(currentPanelType == 0)       new HomePanel(player)
        else if(currentPanelType == 1)  new NeutralPanel()
        else if(currentPanelType == 2)  new DropPanel()
        else if(currentPanelType == 3)  new BonusPanel()
        else                            new EncounterPanel()
    }

    private var players: Array[IPlayer] = Array()
    private var currentPanelType: Int = 0
    private val mapSize: Int = 20
}