package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.helpers.PointMath
import com.maximperevalov.shapeeditor.domain.helpers.vector
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

class LineWithDoubleCircle(
    startX: Float,
    startY: Float,
    endX: Float,
    endY: Float,
    radius: Float = 100F,
    style: Style
) : Shape(style) {

    private val connectionLine = Line(startX, startY, endX, endY, style)
    private val c1 = Circle(startX, startY, radius, style)
    private val c2 = Circle(endX, endY, radius, style)

    private var isNeedLineReposition = true

    private fun updateLinePosition() {
        val center1 = PointMath(c1.centerX, c1.centerY)
        val center2 = PointMath(c2.centerX, c2.centerY)

        val vector1 = vector(center1, center2).normalize() * radius
        val vector2 = vector(center2, center1).normalize() * radius

        val startLinePoint = vector1 + center1
        val endLinePoint = vector2 + center2

        connectionLine.startX = startLinePoint.x
        connectionLine.startY = startLinePoint.y

        connectionLine.endX = endLinePoint.x
        connectionLine.endY = endLinePoint.y
    }

    var startX: Float = startX
        set(value) {
            field = value
            connectionLine.startX = value
            c1.centerX = value
            isNeedLineReposition = true
        }

    var startY: Float = startY
        set(value) {
            field = value
            connectionLine.startY = value
            c1.centerY = value
            isNeedLineReposition = true
        }

    var endX: Float = endX
        set(value) {
            field = value
            connectionLine.endX = value
            c2.centerX = value
            isNeedLineReposition = true
        }

    var endY: Float = endY
        set(value) {
            field = value
            connectionLine.endY = value
            c2.centerY = value
            isNeedLineReposition = true
        }

    var radius: Float = radius
        set(value) {
            field = value
            c1.radius = value
            c2.radius = value
            isNeedLineReposition = true
        }

    override var style: Style = style
        set(value) {
            field = value
            connectionLine.style = value
            c1.style = value
            c2.style = value
        }


    override fun draw(drawer: Drawer) {
        c1.draw(drawer)
        c2.draw(drawer)
        if (isNeedLineReposition) {
            updateLinePosition()
            isNeedLineReposition = false
        }
        connectionLine.draw(drawer)
    }
}
