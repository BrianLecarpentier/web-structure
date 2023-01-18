package org.example.webspark.models

import org.example.webspark.interfaces.LightObserverInterface

class Light : Thing(){

    var lightChange: LightObserverInterface? = null

    var isLightOn: Boolean = false
        set(value) {
            if(state == State.REACHABLE) {
                field = value
            }
        }

    fun toggleLight() {
        if(state == State.REACHABLE) {
            isLightOn = !isLightOn
            lightChange?.onLighChange(this)
        }
    }

    override fun getTypeName(): String = "Light"
    override fun getDescription(): String = "Light name-$name, reachable=$state, is $isLightOn"
}