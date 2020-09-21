package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.shapes.Point
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

private val DEFAULT_POINT_COLOR = Color.BLUE

/**
 * Керує процесом малювання точки
 */
class PointDrawingHandler(private val shapes: ArrayList<Shape>) : ShapeDrawingHandler {

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        val point = Point(firstX, firstY, Style(fillColor = null, Stroke(6F, DEFAULT_POINT_COLOR)))
        shapes.add(point)
    }

    override fun onMove(newX: Float, newY: Float) {}

    override fun onLastTouch(lastX: Float, lastY: Float) {}
}
