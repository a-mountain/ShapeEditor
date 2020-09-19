package com.maximperevalov.shapeeditor.domain.handlers

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeCreatingHandler
import com.maximperevalov.shapeeditor.domain.shapes.Rectangle
import com.maximperevalov.shapeeditor.domain.styles.RectangleStyle

private val DEFAULT_RECT_COLOR = Color.ORANGE

class RectangleShapeCreatingHandler(
    private val shapes: ArrayList<Shape>,
) : ShapeCreatingHandler {

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
        rectangle?.style?.isFill = true
        rectangle?.style?.color = DEFAULT_RECT_COLOR
    }

    private fun createSelectionRectangle(x: Float, y: Float) =
        Rectangle(x, y, x, y, RectangleStyle(color = DEFAULT_SELECTION_COLOR, isFill = false))
}
