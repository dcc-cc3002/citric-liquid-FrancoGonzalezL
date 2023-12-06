package cl.uchile.dcc.citric
package model.panel

import model.unit.player.IPlayer

import cl.uchile.dcc.citric.model.unit.IUnit
import cl.uchile.dcc.citric.model.unit.wildUnit.IWildUnit

import scala.collection.mutable.ArrayBuffer

/** Abstract class representing a Panel. */
abstract class APanel extends Panel {

    /** Array of the characters currently positioned on this panel.
     *
     * In the game, multiple characters might be on the same panel at once, e.g., if multiple players
     * land on the same space.
     */
    private val _characters: ArrayBuffer[IPlayer] = ArrayBuffer()

    /** An array of panels that are directly connected to this one.
     *
     * In the context of the game, multiple routes or paths may exist, this could represent the
     * possible next steps a player might take after being on this panel.
     *
     * @return a List of Panel instances that are adjacent or connected to this panel.
     */
    private val _nextPanels: ArrayBuffer[Panel] = ArrayBuffer()

    override def nextPanels: ArrayBuffer[Panel] = _nextPanels.clone()

    override def characters: ArrayBuffer[IPlayer] = _characters.clone()

    override def containsCharacter(character: IPlayer): Boolean = {
        _characters.contains(character)
    }

    override def charactersCount: Int = _characters.size

    override def containsNextPanel(otherPanel: Panel): Boolean = {
        _nextPanels.contains(otherPanel)
    }

    override def nextPanelsCount: Int = _nextPanels.size

    override def addCharacter(player: IPlayer): Boolean = {
        if(!containsCharacter(player)){
            _characters.addOne(player)
            true
        }else false
    }

    override def removeCharacter(player: IPlayer): Boolean = {
        if (!containsCharacter(player)) false
        else{
            val index: Int = _characters.indexOf(player)
            _characters.remove(index)
            true
        }
    }

    override def addNextPanel(otherPanel: Panel): Boolean = {
        /*  Can not link to itself, nor can add the same panel twice. */
        if (this == otherPanel || this.containsNextPanel(otherPanel))
            false
        else{
            _nextPanels.addOne(otherPanel)
            true
        }
    }

    override def removeNextPanel(otherPanel: Panel): Boolean = {
        if (!this.containsNextPanel(otherPanel)) false
        else{
            val index: Int = _nextPanels.indexOf(otherPanel)
            _nextPanels.remove(index)
            true
        }
    }

    override def canStopHere(player: IPlayer): Boolean = false

    override def wildUnit: Option[IUnit] = None

    override def toString: String = {
        var s: String =
            s"""Type of Panel: ${this.getClass.getSimpleName}
               |Players on This Panel: |""".stripMargin
        characters.foreach( char => {
            s += s"${char.name}, "
        })

        s
    }
}