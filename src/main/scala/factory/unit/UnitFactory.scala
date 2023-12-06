package cl.uchile.dcc.citric
package factory.unit

/**
 * Trait defining a generic blueprint for a UnitFactory.
 *
 * A UnitFactory is responsible for creating units (like characters or NPCs) with specified parameters and random statistics.
 *
 * @tparam T The type of unit that this factory will produce.
 */
trait UnitFactory[+T] {

    /**
     * Sets up random statistics for the units to be created.
     *
     * This method should initialize any random number generators or statistical models needed to assign random attributes to the units.
     */
    def setRandomStats(): Unit

    /**
     * Creates a unit with the given name and random statistics that have been previously set.
     *
     * @param name The name to be assigned to the created unit.
     * @return A new instance of type T with a unique set of attributes.
     */
    def createUnit(name: String): T

    /**
     * Checks if the factory is ready to create units.
     *
     * This might involve checking if the minimum and maximum values for the stats have been set or if other necessary configurations have been completed.
     *
     * @return A boolean indicating whether the factory is properly configured and ready to produce units.
     */
    def factoryIsReady: Boolean

    /**
     * Sets the minimum and maximum values for the random statistics of the units.
     *
     * @param min The minimum value that a random stat can take.
     * @param max The maximum value that a random stat can take.
     */
    def setMinMax(min: Int, max: Int): Unit
}
