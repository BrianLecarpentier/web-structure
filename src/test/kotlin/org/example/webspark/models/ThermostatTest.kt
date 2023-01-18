package org.example.webspark.models

import org.junit.jupiter.api.Assertions.*
import kotlin.test.BeforeTest
import kotlin.test.Test

class ThermostatTest {
    private lateinit var thermostat: Thermostat

    @BeforeTest
    fun before() {
        thermostat = Thermostat()
    }

    @Test
    fun `thermostat in valid initial state` () {
        //Assert
        assertEquals(20.5F, thermostat.currentTemp)
        assertEquals(Thing.State.REACHABLE, thermostat.state)
    }

    @Test
    fun `thermostat set temperature in range success` () {
        //Assert
        thermostat.currentTemp = 50F

        assertEquals(50F, thermostat.currentTemp)
        assertEquals(Thing.State.REACHABLE, thermostat.state)
    }

    @Test
    fun `thermostat set temperatoure out of range fail` () {
        //Assert
        thermostat.currentTemp = 105F

        assertEquals(20.5F, thermostat.currentTemp)
        assertEquals(Thing.State.REACHABLE, thermostat.state)
    }

    @Test
    fun `thermostat set temperatoure lower of range fail` () {
        //Assert
        thermostat.currentTemp = 5F

        assertEquals(20.5F, thermostat.currentTemp)
        assertEquals(Thing.State.REACHABLE, thermostat.state)
    }
}