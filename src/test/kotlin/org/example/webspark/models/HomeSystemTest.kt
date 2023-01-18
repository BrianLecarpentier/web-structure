package org.example.webspark.models

import kotlin.test.*

class HomeSystemTest {
    private lateinit var homeSystem: HomeSystem

    @BeforeTest
    fun before(){
        homeSystem = HomeSystem()
    }

    @Test
    fun `initial lights == 0`() {
        assertEquals(0, homeSystem.thingsList.size)
    }

    @Test
    fun `add 1 light success`() {
        homeSystem.createLight(Thing.State.UNREACHABLE, false, "Light 1")
        assertEquals(1, homeSystem.thingsList.size)
    }

    @Test
    fun `add 2 light success`() {
        homeSystem.createLight(Thing.State.UNREACHABLE, false, "Light 1")
        homeSystem.createLight(Thing.State.UNREACHABLE, false, "Light 2")
        assertEquals(2, homeSystem.thingsList.size)
    }

    @Test
    fun `toggle lights off should off all lights`() {
        homeSystem.createLight(Thing.State.REACHABLE, true, "Light 1")
        homeSystem.createLight(Thing.State.REACHABLE, true, "Light 2")

        homeSystem.toggleLights(false)

        val lights: List<Light> = homeSystem.getLights()

        for (light in lights) {
            assertFalse(light.isLightOn)
        }
    }

    @Test
    fun `toggle lights on should on all lights`() {
        homeSystem.createLight(Thing.State.REACHABLE, false, "Light 1")
        homeSystem.createLight(Thing.State.REACHABLE, false, "Light 2")

        homeSystem.toggleLights(true)

        val lights: List<Light> = homeSystem.getLights()

        for (light in lights) {
            assertTrue(light.isLightOn)
        }
    }

    @Test
    fun `toggle lights off should fail if system status if off`() {
        homeSystem.systemStatus = false
        homeSystem.createLight(Thing.State.REACHABLE, true, "Light 1")
        homeSystem.createLight(Thing.State.REACHABLE, true, "Light 2")

        homeSystem.toggleLights(false)

        val lights: List<Light> = homeSystem.getLights()

        for (light in lights) {
            assertTrue(light.isLightOn)
        }
    }

    @Test
    fun `toggle lights on should fail if system status if off`() {
        homeSystem.systemStatus = false
        homeSystem.createLight(Thing.State.REACHABLE, false, "Light 1")
        homeSystem.createLight(Thing.State.REACHABLE, false, "Light 2")

        homeSystem.toggleLights(true)

        val lights: List<Light> = homeSystem.getLights()

        for (light in lights) {
            assertFalse(light.isLightOn)
        }
    }
}