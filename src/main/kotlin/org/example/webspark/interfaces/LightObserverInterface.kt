package org.example.webspark.interfaces

import org.example.webspark.models.Light

interface LightObserverInterface {
    fun onLighChange(light: Light)
}