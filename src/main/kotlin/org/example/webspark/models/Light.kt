package org.example.webspark.models

import org.example.webspark.interfaces.LightObserverInterface

class Light : Thing(){

    var lightChange: LightObserverInterface? = null

    var isLightOn: Boolean = false

    fun toggleLight() {
        this.isLightOn = !isLightOn
        lightChange?.onLighChange(this)
    }

    override fun getTypeName(): String = "Light"
    override fun getDescription(): String = "Light name-$name, reachable=$state, is $isLightOn"
}