package cl.uchile.dcc.citric
package model.panel

import model.unit.player.IPlayer

import cl.uchile.dcc.citric.model.unit.IUnit

import scala.collection.mutable.ArrayBuffer

/** Abstract class representing a Panel. */
abstract class APanel extends Panel {

    /** Array of the characters currently positioned on this panel.
     *
     * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
     * land on the same space.
     */
    private val characters: ArrayBuffer[IPlayer] = new ArrayBuffer[IPlayer]()

    /** An array of panels that are directly connected to this one.
     *
     * In the context of the game, multiple routes or paths may exist, this could represent the
     * possible next steps a player might take after being on this panel.
     *
     * @return a List of Panel instances that are adjacent or connected to this panel.
     */
    private val nextPanels: ArrayBuffer[Panel] = new ArrayBuffer[Panel]()

    override def containsCharacter(character: IPlayer): Boolean = {
        if (characters.indexOf(character) == -1)
            false
        else
            true
    }

    override def charactersCount: Int = characters.size

    override def isPrevTo(otherPanel: Panel): Boolean = {
        if(nextPanels.indexOf(otherPanel) == -1)
            false
        else
            true
    }

    override def nextPanelsCount: Int = nextPanels.size

    override def panelType: String = this.getClass.getSimpleName

    override def addCharacter(player: IPlayer): Boolean = {
        if(!containsCharacter(player)){
            characters.addOne(player)
            true
        }else false
    }

    override def removeCharacter(player: IPlayer): Boolean = {
        if (!containsCharacter(player)) false
        else{
            val index = characters.indexOf(player)
            characters.remove(index)
            true
        }
    }

    override def addNextPanel(otherPanel: Panel): Boolean = {
        /*  Can not link to itself, nor can add the same panel twice. */
        if (this == otherPanel || this.isPrevTo(otherPanel))
            false
        else{
            nextPanels.addOne(otherPanel)
            true
        }
    }
}
