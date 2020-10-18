package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 *  Репрезентує - прямокутник
 */
class Rectangle(
    var x: Float, var y: Float, var width: Float, var height: Float, style: Style,
) : Shape(style) {

    override fun draw(drawer: Drawer) {
        drawer.drawRect(x, y, width, height, style)
    }
}
