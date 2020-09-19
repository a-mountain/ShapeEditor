package com.maximperevalov.shapeeditor.domain.helpers

import kotlin.math.pow
import kotlin.math.sqrt

/**
 * рахує відстань між двома точками
 */
fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    return sqrt((y2 - y1).pow(2) + (x2 - x1).pow(2))
}

data class PointMath(val x: Float, val y: Float) {

    fun magnitude() = sqrt(x * x + y * y)

    fun normalize(): PointMath {
        val mag = magnitude()
        return if (mag == 0.0F) PointMath(0.0F, 0.0F) else PointMath(x / mag, y / mag)
    }

    fun midpoint(pointMath: PointMath) = PointMath(
        pointMath.x + (this.x - pointMath.x) / 2.0F,
        pointMath.y + (this.y - pointMath.y) / 2.0F
    )

    fun distance(pointMath: PointMath): Double {
        val a = this.x - pointMath.x
        val b = this.y - pointMath.y
        return sqrt(a * a + b * b.toDouble())
    }

    operator fun plus(increment: PointMath) = PointMath(this.x + increment.x, this.y + increment.y)

    operator fun minus(decrement: PointMath) = PointMath(this.x - decrement.x, this.y - decrement.y)

    operator fun times(number: Float): PointMath = PointMath(this.x * number, this.y * number)

    operator fun times(number: Int): PointMath = PointMath(this.x * number, this.y * number)
}

operator fun Int.times(pointMath: PointMath) = pointMath * this

operator fun Float.times(pointMath: PointMath) = pointMath * this

fun vector(start: PointMath, end: PointMath) = PointMath(end.x - start.x, end.y - start.y)