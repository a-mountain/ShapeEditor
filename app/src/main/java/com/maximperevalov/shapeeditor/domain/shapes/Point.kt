package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.styles.PointStyle

class Point(var x: Float, var y: Float, var style: PointStyle) : Shape {
    override fun draw(drawer: Drawer) {
        drawer.drawPoint(x, y, style)
    }
}