package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.styles.RectangleStyle
import com.maximperevalov.shapeeditor.domain.Shape

class Rectangle(
    var x: Float, var y: Float, var width: Float, var height: Float, var style: RectangleStyle,
) : Shape {

    override fun draw(drawer: Drawer) {
        drawer.drawRect(x, y, width, height, style)
    }
}