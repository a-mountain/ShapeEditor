package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Репрезентує - коло.
 */
class Circle(var centerX: Float, var centerY: Float, var radius: Float, style: Style) :
    Shape(style) {

    override fun draw(drawer: Drawer) {
        drawer.drawCircle(centerX, centerY, radius, style)
    }
}