package com.maximperevalov.shapeeditor.domain.handlers

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeCreatingHandler
import com.maximperevalov.shapeeditor.domain.shapes.Point
import com.maximperevalov.shapeeditor.domain.styles.PointStyle

private val DEFAULT_POINT_COLOR = Color.BLUE

class PointShapeCreatingHandler(
    private val shapes: ArrayList<Shape>,
) : ShapeCreatingHandler {

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        val point = Point(firstX, firstY, PointStyle(color = DEFAULT_POINT_COLOR, strokeWith = 6F))
        shapes.add(point)
    }

    override fun onMove(newX: Float, newY: Float) {}

    override fun onLastTouch(lastX: Float, lastY: Float) {}
}