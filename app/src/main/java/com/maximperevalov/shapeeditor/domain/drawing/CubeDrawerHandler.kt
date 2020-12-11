package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.editor.ShapeEditor
import com.maximperevalov.shapeeditor.domain.helpers.PointMath
import com.maximperevalov.shapeeditor.domain.shapes.Cube
import com.maximperevalov.shapeeditor.domain.shapes.styles.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

class CubeDrawerHandler(style: Style, shapes: ArrayList<Shape>) :
    ShapeDrawingHandler(style, shapes) {

    private lateinit var cube: Cube
    private lateinit var firstPoint: PointMath

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        cube = createSelectionCube(firstX, firstY)
        firstPoint = PointMath(firstX, firstY)
        addShape(cube)
    }

    override fun onMove(newX: Float, newY: Float) {
        cube.apply {
            val newP = PointMath(newX, newY)
            val distance = newP.distance(firstPoint)
            x2 = x1 + distance
            y2 = y1 + distance
        }
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        applyStyle(cube)
        ShapeEditor.getInstance().eventEmitter.emitDrawNewShapeEvent(cube)
    }

    private fun createSelectionCube(x: Float, y: Float) =
        Cube(x, y, x, y, Style.createAbsoluteTransparentStyle(Stroke(5F, DEFAULT_SELECTION_COLOR)))
}