package com.maximperevalov.shapeeditor.domain.helpers

import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.sqrt

/**
 * Домогає з розрахунками, які стосуються точок і векторів.
 */
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

    fun distance(pointMath: PointMath): Float {
        val ac = abs(this.x - pointMath.x)
        val cb = abs(this.y - pointMath.y)
        return hypot(cb, ac)
    }

    operator fun plus(increment: PointMath) = PointMath(this.x + increment.x, this.y + increment.y)

    operator fun minus(decrement: PointMath) = PointMath(this.x - decrement.x, this.y - decrement.y)

    operator fun times(number: Float): PointMath = PointMath(this.x * number, this.y * number)

    operator fun times(number: Int): PointMath = PointMath(this.x * number, this.y * number)
}

operator fun Int.times(pointMath: PointMath) = pointMath * this

operator fun Float.times(pointMath: PointMath) = pointMath * this

fun vector(start: PointMath, end: PointMath) = PointMath(end.x - start.x, end.y - start.y)

fun distance(x1: Float, x2: Float, y1: Float, y2: Float): Float {
    val a = x2 - x1
    val b = y2 - y1
    return sqrt(a * a + b * b)
}