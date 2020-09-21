package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.shapes.Rectangle
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

private val DEFAULT_RECT_COLOR = Color.ORANGE

/**
 * Керує процесом малювання прямокутника
 */
class RectangleDrawingHandler(private val shapes: ArrayList<Shape>) : ShapeDrawingHandler {

    private var rectangle: Rectangle? = null

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        rectangle = createSelectionRectangle(firstX, firstY)
        shapes.add(rectangle!!)
    }

    override fun onMove(newX: Float, newY: Float) {
        rectangle?.apply {
            width = newX
            height = newY
        }
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        makeRectangleReal()
    }

    private fun makeRectangleReal() {
        rectangle?.style?.fillColor = DEFAULT_RECT_COLOR
    }

    private fun createSelectionRectangle(x: Float, y: Float) =
        Rectangle(
            x, y, x, y, Style(fillColor = null, Stroke(width = 5F, color = DEFAULT_SELECTION_COLOR))
        )
}