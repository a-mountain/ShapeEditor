package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.shapes.Point
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Керує процесом малювання точки
 */
class PointDrawingHandler(private val shapes: ArrayList<Shape>, style: Style) :
    ShapeDrawingHandler(style) {

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        val point = Point(firstX, firstY, currentShapeStyle.stroke.color, radius = 6F)
        shapes.add(point)
    }

    override fun onMove(newX: Float, newY: Float) {}

    override fun onLastTouch(lastX: Float, lastY: Float) {}
}
