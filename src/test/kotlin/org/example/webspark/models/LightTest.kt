package org.example.webspark.models

import org.example.webspark.interfaces.LightObserverInterface
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.mockito.kotlin.mock
import kotlin.test.BeforeTest
import kotlin.test.Test

class LightTest {

    private lateinit var light: Light

    @BeforeTest
    fun before(){
        light = Light()
    }

    @Test
    fun `light in valid initial state` () {
        /// AAA

        //Arrange

        //Act

        //Assert
        assertFalse(light.isLightOn)
        assertEquals(Thing.State.REACHABLE, light.state)
    }

    @Test
    fun `toggle light on success`() {
        //Arrange

        //Act
        light.toggleLight()

        //Assert
        assertTrue(light.isLightOn)
        assertEquals(Thing.State.REACHABLE, light.state)
    }

    @Test
    fun `toggle light off success`() {
        //Arrange
        light.isLightOn = true

        //Act
        light.toggleLight()

        //Assert
        assertFalse(light.isLightOn)
        assertEquals(Thing.State.REACHABLE, light.state)
    }

    @Test
    fun `toggle light on should fail if light is unreachable`() {
        //Arrange
        light.state = Thing.State.UNREACHABLE

        //Act
        light.toggleLight()

        //Assert
        assertFalse(light.isLightOn)
        assertEquals(Thing.State.UNREACHABLE, light.state)
    }

    @Test
    fun `set light triggers listener`(){
        val fakeListener = mock<LightObserverInterface>()
        light.lightChange = fakeListener

        light.toggleLight()

        Mockito.verify(fakeListener).onLighChange(light)
    }
}