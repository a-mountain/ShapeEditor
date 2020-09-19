package com.maximperevalov.shapeeditor.domain.handlers

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeCreatingHandler
import com.maximperevalov.shapeeditor.domain.shapes.Line
import com.maximperevalov.shapeeditor.domain.styles.LineStyle

private val DEFAULT_LINE_COLOR = Color.GREEN

class LineShapeCreatingHandler(
    private val shapes: ArrayList<Shape>,
) : ShapeCreatingHandler {

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
        line?.style?.color = DEFAULT_LINE_COLOR
    }

    private fun createSelectionLine(x: Float, y: Float) =
        Line(x, y, x, y, LineStyle(color = DEFAULT_SELECTION_COLOR))
}