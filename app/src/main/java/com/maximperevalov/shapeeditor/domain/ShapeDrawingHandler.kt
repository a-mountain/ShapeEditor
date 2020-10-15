package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

val DEFAULT_SELECTION_COLOR = Color.BLACK
const val DEFAULT_STROKE_WIDTH = 5F

val DEFAULT_STYLE =
    Style(fillColor = Color.PURPLE, Stroke(DEFAULT_STROKE_WIDTH, Color.ORANGE))


/**
 *  ShapeCreatingHandler - керує процесом малювання фігури
 */

abstract class ShapeDrawingHandler(open var currentShapeStyle: Style = DEFAULT_STYLE) {

    abstract fun onFirstTouch(firstX: Float, firstY: Float)

    abstract fun onMove(newX: Float, newY: Float)

    abstract fun onLastTouch(lastX: Float, lastY: Float)

}
