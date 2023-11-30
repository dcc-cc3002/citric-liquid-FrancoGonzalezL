package cl.uchile.dcc.citric
package model.panel

import model.unit.player.IPlayer

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

    override def getNextPanelByIndex(index: Int): Panel = {
        val panel: Option[Panel] =
            _nextPanels.find(panel => {
                _nextPanels.indexOf(panel) == index
            })
        if(panel.isDefined) panel.get
        else throw new AssertionError("Panel not found")
    }

    override def getCharacterByIndex(index: Int): IPlayer = {
        val player: Option[IPlayer] =
            _characters.find(player => {
                _characters.indexOf(player) == index
            })
        if (player.isDefined) player.get
        else throw new AssertionError("Player not found in Panel")
    }

    override def nextPanelsToString(init: Int): String = {
        var s: String = ""
        _nextPanels.foreach(panel => {
            s += s"${_nextPanels.indexOf(panel)+init} -> Panel type: ${panel.getClass.getSimpleName}, N of Players: ${panel.charactersCount}\n"
        })
        s
    }

    override def charactersToString(init: Int): String = {
        var s: String = ""
        _characters.foreach(player => {
            s += s"${_characters.indexOf(player)+init} -> Name: ${player.name}, Hp: ${player.hp}, attack: ${player.attack}\n"
        })
        s
    }

    override def canStopHere(player: IPlayer): Boolean = false
}
