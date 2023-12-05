package cl.uchile.dcc.citric
package exceptions

class FactoryConfigurationException(details: String) extends Exception(s"An invalid Configuration found on a Factory --$details") {

}
