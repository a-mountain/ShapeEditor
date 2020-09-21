package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Репрезентує - еліпс
 */
class Ellipse(
    var x: Float, var y: Float, var width: Float, var height: Float, var style: Style,
) : Shape {
    override fun draw(drawer: Drawer) {
        drawer.drawEllipse(x, y, width, height, style)
    }
}
