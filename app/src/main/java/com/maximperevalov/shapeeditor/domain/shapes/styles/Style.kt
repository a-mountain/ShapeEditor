package com.maximperevalov.shapeeditor.domain.shapes.styles

import com.maximperevalov.shapeeditor.domain.Color

val DEFAULT_SELECTION_COLOR = Color.BLACK
const val DEFAULT_STROKE_WIDTH = 5F
val DEFAULT_STYLE =
    Style(Color.PURPLE, Stroke(DEFAULT_STROKE_WIDTH, Color.ORANGE))

/**
 * Містить, "космітичні" атрибути фігур, такі як: колір, обедення
 */
data class Style(
    val fillColor: Color,
    val stroke: Stroke,
    val isAbsoluteTransparent: Boolean,
    val isStrokeless: Boolean,
) {

    constructor(fillColor: Color, stroke: Stroke) : this(fillColor, stroke, false, false)

    companion object {
        fun createAbsoluteTransparentStyle(stroke: Stroke) = Style(
            DEFAULT_STYLE.fillColor,
            stroke,
            isAbsoluteTransparent = true,
            isStrokeless = false
        )

        fun createStrokelessStyle(fillColor: Color) = Style(
            fillColor,
            DEFAULT_STYLE.stroke.copy(),
            isAbsoluteTransparent = false,
            isStrokeless = true
        )
    }

    fun withIsStrokeless(isStrokeless: Boolean) = this.copy(isStrokeless = isStrokeless)

    fun withIsAbsoluteTransparent(isAbsoluteTransparent: Boolean) =
        this.copy(isAbsoluteTransparent = isAbsoluteTransparent)

    fun withStrokeWidth(width: Float) = this.copy(stroke = this.stroke.copy(width = width))

    fun withStrokeColor(color: Color) = this.copy(stroke = this.stroke.copy(color = color))

    fun withFillColor(color: Color) = this.copy(fillColor = color)
}
