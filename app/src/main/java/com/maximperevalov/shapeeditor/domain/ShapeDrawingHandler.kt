package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.styles.DEFAULT_STYLE
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 *  ShapeCreatingHandler - керує процесом малювання фігури
 */

abstract class ShapeDrawingHandler(open var currentShapeStyle: Style = DEFAULT_STYLE) {

    abstract fun onFirstTouch(firstX: Float, firstY: Float)

    abstract fun onMove(newX: Float, newY: Float)

    abstract fun onLastTouch(lastX: Float, lastY: Float)
}
