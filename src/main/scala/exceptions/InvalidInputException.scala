package cl.uchile.dcc.citric
package exceptions

class InvalidInputException(details: String) extends Exception(s"An invalid Input has been received --$details")
