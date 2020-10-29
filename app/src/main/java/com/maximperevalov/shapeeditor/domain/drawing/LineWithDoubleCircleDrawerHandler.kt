package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.shapes.LineWithDoubleCircle
import com.maximperevalov.shapeeditor.domain.shapes.styles.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

class LineWithDoubleCircleDrawerHandler(private val shapes: ArrayList<Shape>, style: Style) :
    ShapeDrawingHandler(style) {

    private var lineWithDoubleCircle: LineWithDoubleCircle? = null

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        lineWithDoubleCircle = createSelectionLineWithDoubleCircle(firstX, firstY)
        shapes.add(lineWithDoubleCircle!!)
    }

    override fun onMove(newX: Float, newY: Float) {
        lineWithDoubleCircle?.endX = newX
        lineWithDoubleCircle?.endY = newY
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        makeLineWithDoubleCircleReal()
    }

    private fun makeLineWithDoubleCircleReal() {
        lineWithDoubleCircle?.style = currentShapeStyle.copy()
    }

    private fun createSelectionLineWithDoubleCircle(x: Float, y: Float) =
        LineWithDoubleCircle(
            x,
            y,
            x,
            y,
            100F,
            Style.createAbsoluteTransparentStyle(Stroke(6F, DEFAULT_SELECTION_COLOR))
        )
}
