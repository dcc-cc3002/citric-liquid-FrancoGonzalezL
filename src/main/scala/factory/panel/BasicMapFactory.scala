package cl.uchile.dcc.citric
package factory.panel
import model.panel.Panel
import model.unit.player.IPlayer
import exceptions.FactoryConfigurationException
import model.panel.concretePanel.{BonusPanel, DropPanel, EncounterPanel, HomePanel, NeutralPanel}

/** Basic implementation of the MapFactory for creating a simple game map.
 *
 * This factory generates a circular map where panels are distributed evenly among the players.
 */
class BasicMapFactory extends MapFactory {

    /** Creates a circular game map with a designated number of panels per player.
     *
     * Throws an exception if players have not been set before creating the map.
     */
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

                /* it means that is a Home Panel */
                if(current.get.canStopHere(player))
                    player.moveToPanel(current.get)// All players are initially on their Home Panel.

                // Prepare for the next type of panel to be created.
                generateNextType()
            }
        })

        if(head.isDefined && !current.get.equals(head.get))
            current.get.addNextPanel(head.get)
    }

    override def setPlayers(players: Array[IPlayer]): Unit = {
        this.players = players
    }

    /**
     * Generates the type for the next panel to be created, cycling through the types.
     */
    private def generateNextType(): Unit = {
        currentPanelType = (currentPanelType +  1) % 5
    }

    /** Creates a panel of the current type and returns it.
     *
     * @param player The player for whom the panel is being created.
     * @return A new Panel object of the appropriate type.
     */
    private def createPanel(player: IPlayer): Panel = {

        if     (currentPanelType == 0)  new HomePanel(player)
        else if(currentPanelType == 1)  new NeutralPanel()
        else if(currentPanelType == 2)  new DropPanel()
        else if(currentPanelType == 3)  new BonusPanel()
        else                            new EncounterPanel()
    }

    // Array of players for whom the map is being created.
    private var players: Array[IPlayer] = Array()
    // The current type of panel being created, cycled through with generateNextType.
    private var currentPanelType: Int = 0
    // The total number of panels that will be created for the map.
    private val mapSize: Int = 20
}