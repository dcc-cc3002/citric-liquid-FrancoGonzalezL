package cl.uchile.dcc.citric
package model.panel

import model.unit.player.IPlayer
import scala.collection.mutable.ArrayBuffer

/** Abstract class representing a Panel */
abstract class APanel extends Panel {

    override val characters: ArrayBuffer[IPlayer] = new ArrayBuffer[IPlayer]()

    override val nextPanels: ArrayBuffer[Panel] = new ArrayBuffer[Panel]()
    // Link to itself (single panel).
    nextPanels.addOne(this)

    override def panelType: String = this.getClass.getSimpleName

    override def addCharacter(player: IPlayer): Unit = {
        if(characters.indexOf(player) == -1 ){
            characters.addOne(player)
        }
    }
    override def removeCharacter(player: IPlayer): Unit = {
        val index = characters.indexOf(player)
        if (index != -1) {
            characters.remove(index)
        }
    }

    /**Adds a Panel to the list of nextPanels.
     *
     * Only single Panels are connected to itself.
     *
     * To link two Panels normally (multi_link_mode = false), link the otherPanel to all the nextPanels of thisPanel
     * and remove all the nextPanels of thisPanel, and then link thisPanel to the otherPanel.
     *
     * To link two Panels with the second method (multi_link_mode = true), link thisPanel to the otherPanel and
     * link the otherPanel to thisPanel (remove the link to itself)
     *
     * @param otherPanel      The Panel to link, it must be a single panel.
     * @param multi_link_mode Choose the method for linking the panels.
     */
    override def addNextPanel(otherPanel: Panel, multi_link_mode: Boolean): Unit = {
        /*
        Can not link to itself.
        Other Panel must be a single Panel ().
        Can't add two times the same Panel.
         */
        if (this == otherPanel ||
            otherPanel.nextPanels.indexOf(otherPanel) == -1 ||
            nextPanels.indexOf(otherPanel) != -1)
            return
        //If this Panel is a single Panel, link normally (single Panels are linked to itself).
        if(multi_link_mode && nextPanels.indexOf(this) == -1){
            //Link this Panel to otherPanel.
            nextPanels.addOne(otherPanel)
            //OtherPanel should be linked only to this.
            otherPanel.nextPanels.clear()
            otherPanel.nextPanels.addOne(this)
        }else{
            //Link otherPanel to all the Panels next to this panel.
            for(panel <- nextPanels){
                otherPanel.nextPanels.addOne(panel)
            }
            //This panel should be linked only to the otherPanel.
            nextPanels.clear()
            nextPanels.addOne(otherPanel)
        }
    }
}
