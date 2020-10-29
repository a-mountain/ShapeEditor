package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Репрезентує - лінію.
 */
class Line(
    var startX: Float, var startY: Float, var endX: Float, var endY: Float, style: Style,
) : Shape(style) {

    constructor(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        color: Color,
        width: Float,
    ) : this(
        startX, startY, endX, endY, Style.createAbsoluteTransparentStyle(
            Stroke(width, color)
        )
    )

    override fun draw(drawer: Drawer) {
        drawer.drawLine(startX, startY, endX, endY, style)
    }

}
