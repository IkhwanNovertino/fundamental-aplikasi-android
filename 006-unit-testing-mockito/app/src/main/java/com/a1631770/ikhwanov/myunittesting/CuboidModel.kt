package com.a1631770.ikhwanov.myunittesting

class CuboidModel {
    private var length: Double = 0.0
    private var width: Double = 0.0
    private var heigth: Double = 0.0

    fun save(length: Double, width: Double, heigth: Double){
        this.length = length
        this.width = width
        this.heigth = heigth
    }

    fun getVolume(): Double = length * width * heigth

    fun getSurfaceArea(): Double {
        val lw = length * width
        val wh = width * heigth
        val lh = length * heigth

        return 2 * (lw + lh + wh)
    }

    fun getCircumference() = 4 * (length + width + heigth)


}