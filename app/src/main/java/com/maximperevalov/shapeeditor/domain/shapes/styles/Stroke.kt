package com.maximperevalov.shapeeditor.domain.shapes.styles

import com.maximperevalov.shapeeditor.domain.Color

/**
 * Репрезентує - обведення.
 */
data class Stroke(val width: Float, val color: Color, val hasDash: Boolean = false)
