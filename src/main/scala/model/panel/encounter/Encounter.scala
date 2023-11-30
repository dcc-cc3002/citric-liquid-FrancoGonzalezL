package cl.uchile.dcc.citric
package model.panel.encounter

import model.panel.Panel

import model.unit.IUnit

trait Encounter extends Panel {
    def unit: IUnit
}
