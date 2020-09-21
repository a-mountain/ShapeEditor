package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.shapes.Line
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

private val DEFAULT_LINE_COLOR = Color.GREEN


/**
 *  Керує процесом малювання лінії
 */
class LineDrawingHandler(private val shapes: ArrayList<Shape>) : ShapeDrawingHandler {

    private var line: Line? = null

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        line = createSelectionLine(firstX, firstY)
        shapes.add(line!!)
    }

    override fun onMove(newX: Float, newY: Float) {
        line?.apply {
            endX = newX
            endY = newY
        }
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        makeLineReal()
    }

    private fun makeLineReal() {
        line?.style?.stroke!!.color = DEFAULT_LINE_COLOR
    }

    private fun createSelectionLine(x: Float, y: Float) =
        Line(x, y, x, y, Style(fillColor = null, Stroke(5F, DEFAULT_SELECTION_COLOR)))
}