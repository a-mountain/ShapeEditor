package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Репрезентує - точку
 */
class Point(var x: Float, var y: Float, style: Style) : Shape(style) {

    constructor(x: Float, y: Float, color: Color, radius: Float) : this(
        x, y, Style.createAbsoluteTransparentStyle(Stroke(radius, color))
    )

    override fun draw(drawer: Drawer) {
        drawer.drawPoint(x, y, style)
    }
}
