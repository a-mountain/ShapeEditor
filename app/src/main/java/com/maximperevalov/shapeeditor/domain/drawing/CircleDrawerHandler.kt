package com.maximperevalov.shapeeditor.domain.drawing

import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.helpers.PointMath
import com.maximperevalov.shapeeditor.domain.shapes.Circle
import com.maximperevalov.shapeeditor.domain.shapes.Point
import com.maximperevalov.shapeeditor.domain.shapes.styles.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

class CircleDrawerHandler(private val shapes: ArrayList<Shape>, style: Style) :
    ShapeDrawingHandler(style) {

    private var circle: Circle? = null
    private var centerPoint: Point? = null

    private var centerX: Float = 0F
    private var centerY: Float = 0F

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        centerX = firstX
        centerY = firstY

        centerPoint = createCenterPoint(firstX, firstY)
        circle = createSelectionCircle(firstX, firstY)

        shapes.add(circle!!)
        shapes.add(centerPoint!!)
    }

    override fun onMove(newX: Float, newY: Float) {
        val newPoint = PointMath(newX, newY)
        val center = PointMath(centerX, centerY)
        val distance = newPoint.distance(center)
        circle?.radius = distance
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        shapes.remove(centerPoint!!)
        makeCircleReal()
    }

    private fun makeCircleReal() {
        circle?.style = currentShapeStyle.copy()
    }

    private fun createCenterPoint(x: Float, y: Float) =
        Point(
            x,
            y,
            Style.createAbsoluteTransparentStyle(Stroke(10F, color = DEFAULT_SELECTION_COLOR))
        )

    private fun createSelectionCircle(x: Float, y: Float) =
        Circle(
            x,
            y,
            0F,
            Style.createAbsoluteTransparentStyle(Stroke(6F, DEFAULT_SELECTION_COLOR))
        )
}
