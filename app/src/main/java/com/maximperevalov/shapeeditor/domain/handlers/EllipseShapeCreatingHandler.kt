package com.maximperevalov.shapeeditor.domain.handlers

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeCreatingHandler
import com.maximperevalov.shapeeditor.domain.helpers.PointMath
import com.maximperevalov.shapeeditor.domain.helpers.distance
import com.maximperevalov.shapeeditor.domain.helpers.vector
import com.maximperevalov.shapeeditor.domain.shapes.Ellipse
import com.maximperevalov.shapeeditor.domain.shapes.Point
import com.maximperevalov.shapeeditor.domain.styles.EllipseStyle
import com.maximperevalov.shapeeditor.domain.styles.PointStyle

private val DEFAULT_ELLIPSE_COLOR = Color.WHITE

class EllipseShapeCreatingHandler(
    private val shapes: ArrayList<Shape>,
) : ShapeCreatingHandler {

    private var ellipse: Ellipse? = null
    private var centerPoint: Point? = null

    private var centerX: Float = 0F
    private var centerY: Float = 0F

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        centerX = firstX
        centerY = firstY

        centerPoint = createCenterPoint(firstX, firstY)
        ellipse = createSelectionEllipse(firstX, firstY)

        shapes.add(ellipse!!)
        shapes.add(centerPoint!!)
    }

    override fun onMove(newX: Float, newY: Float) {
        ellipse?.apply {
            val distance = distance(centerX, centerY, newX, newY)
            val vector =
                vector(PointMath(centerX, centerY), PointMath(newX, newY)).normalize() * distance

            x = centerX - vector.x
            y = centerY - vector.y

            width = newX
            height = newY
        }
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        shapes.remove(centerPoint!!)
        makeEllipseReal()
    }

    private fun makeEllipseReal() {
        ellipse?.style?.isFill = true
        ellipse?.style?.color = DEFAULT_ELLIPSE_COLOR
    }

    private fun createCenterPoint(x: Float, y: Float) =
        Point(x, y, PointStyle(DEFAULT_SELECTION_COLOR, 10F))

    private fun createSelectionEllipse(x: Float, y: Float) =
        Ellipse(x, y, x, y, EllipseStyle(color = DEFAULT_SELECTION_COLOR, isFill = false))
}
