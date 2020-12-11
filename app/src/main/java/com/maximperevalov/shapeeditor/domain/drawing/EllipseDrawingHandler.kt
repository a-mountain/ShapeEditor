package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.editor.ShapeEditor
import com.maximperevalov.shapeeditor.domain.helpers.PointMath
import com.maximperevalov.shapeeditor.domain.helpers.vector
import com.maximperevalov.shapeeditor.domain.shapes.Ellipse
import com.maximperevalov.shapeeditor.domain.shapes.Point
import com.maximperevalov.shapeeditor.domain.shapes.styles.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Керує процесом малювання еліпса.
 * Еліпс малюється від центру до одного з кутів охоплюючого прямокутника.
 */
class EllipseDrawingHandler(style: Style, shapes: ArrayList<Shape>) :
    ShapeDrawingHandler(style, shapes) {

    private lateinit var ellipse: Ellipse
    private lateinit var centerPoint: Point

    private var centerX: Float = 0F
    private var centerY: Float = 0F

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        centerX = firstX
        centerY = firstY

        centerPoint = createCenterPoint(firstX, firstY)
        ellipse = createSelectionEllipse(firstX, firstY)

        addShape(ellipse)
        addShape(this.centerPoint)
    }

    override fun onMove(newX: Float, newY: Float) {
        ellipse.apply {
            val oppositeCorner = calcOppositeRectCorner(newX, newY, centerX, centerY)

            x = oppositeCorner.x
            y = oppositeCorner.y

            width = newX
            height = newY
        }
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        removeShape(centerPoint)
        applyStyle(ellipse)
        ShapeEditor.getInstance().eventEmitter.emitDrawNewShapeEvent(ellipse)
    }

    private fun calcOppositeRectCorner(
        cornerX: Float,
        cornerY: Float,
        centerX: Float,
        centerY: Float,
    ): PointMath {
        val rectCorner = PointMath(cornerX, cornerY)
        val rectCenter = PointMath(centerX, centerY)
        val directionToOppositeRectCorner =
            vector(rectCenter, rectCorner).normalize() * rectCenter.distance(rectCorner)
        return rectCenter - directionToOppositeRectCorner
    }

    private fun createCenterPoint(x: Float, y: Float) =
        Point(
            x,
            y,
            Style.createAbsoluteTransparentStyle(Stroke(10F, color = DEFAULT_SELECTION_COLOR))
        )

    private fun createSelectionEllipse(x: Float, y: Float) =
        Ellipse(
            x,
            y,
            x,
            y,
            Style.createAbsoluteTransparentStyle(Stroke(6F, DEFAULT_SELECTION_COLOR))
        )
}
