package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.editor.ShapeEditor
import com.maximperevalov.shapeeditor.domain.shapes.LineWithDoubleCircle
import com.maximperevalov.shapeeditor.domain.shapes.styles.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

class LineWithDoubleCircleDrawerHandler(style: Style, shapes: ArrayList<Shape>) :
    ShapeDrawingHandler(style, shapes) {

    private lateinit var lineWithDoubleCircle: LineWithDoubleCircle

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        lineWithDoubleCircle = createSelectionLineWithDoubleCircle(firstX, firstY)
        addShape(lineWithDoubleCircle)
    }

    override fun onMove(newX: Float, newY: Float) {
        lineWithDoubleCircle.endX = newX
        lineWithDoubleCircle.endY = newY
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        applyStyle(lineWithDoubleCircle)
        ShapeEditor.getInstance().eventEmitter.emitDrawNewShapeEvent(lineWithDoubleCircle)
    }

    private fun createSelectionLineWithDoubleCircle(x: Float, y: Float) =
        LineWithDoubleCircle(
            x,
            y,
            x,
            y,
            style = Style.createAbsoluteTransparentStyle(Stroke(6F, DEFAULT_SELECTION_COLOR))
        )
}
