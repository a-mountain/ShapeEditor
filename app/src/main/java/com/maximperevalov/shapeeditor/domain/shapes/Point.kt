package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Репрезентує - точку
 */
class Point(var x: Float, var y: Float, style: Style) : Shape(style) {
    override fun draw(drawer: Drawer) {
        drawer.drawPoint(x, y, style)
    }
}