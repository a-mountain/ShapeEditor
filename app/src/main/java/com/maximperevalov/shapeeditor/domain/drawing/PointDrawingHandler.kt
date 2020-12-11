package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.editor.ShapeEditor
import com.maximperevalov.shapeeditor.domain.shapes.Point
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Керує процесом малювання точки
 */
class PointDrawingHandler(style: Style, shapes: ArrayList<Shape>) :
    ShapeDrawingHandler(style, shapes) {

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        val point = Point(firstX, firstY, style.stroke.color, radius = 6F)
        addShape(point)
        ShapeEditor.getInstance().eventEmitter.emitDrawNewShapeEvent(point)
    }

    override fun onMove(newX: Float, newY: Float) {}

    override fun onLastTouch(lastX: Float, lastY: Float) {}
}
