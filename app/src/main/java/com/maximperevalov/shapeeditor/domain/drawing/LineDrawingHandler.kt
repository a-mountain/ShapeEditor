package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.editor.ShapeEditor
import com.maximperevalov.shapeeditor.domain.shapes.Line
import com.maximperevalov.shapeeditor.domain.shapes.styles.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 *  Керує процесом малювання лінії
 */
class LineDrawingHandler(style: Style, shapes: ArrayList<Shape>) :
    ShapeDrawingHandler(style, shapes) {

    private lateinit var line: Line

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        line = createSelectionLine(firstX, firstY)
        addShape(line)
    }

    override fun onMove(newX: Float, newY: Float) {
        line.apply {
            endX = newX
            endY = newY
        }
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        applyStyle(line)
        ShapeEditor.getInstance().eventEmitter.emitDrawNewShapeEvent(line)
    }

    private fun createSelectionLine(x: Float, y: Float) =
        Line(x, y, x, y, DEFAULT_SELECTION_COLOR, width = 5F)
}
