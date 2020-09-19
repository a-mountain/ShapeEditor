package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.styles.LineStyle

class Line(
    var startX: Float, var startY: Float, var endX: Float, var endY: Float, var style: LineStyle,
) : Shape {
    override fun draw(drawer: Drawer) {
        drawer.drawLine(startX, startY, endX, endY, style)
    }

}