package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.styles.DEFAULT_STYLE
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 *  ShapeCreatingHandler - керує процесом малювання фігури
 */
abstract class ShapeDrawingHandler(
    protected val style: Style = DEFAULT_STYLE,
    private val shapes: ArrayList<Shape>
) {

    protected fun applyStyle(shape: Shape) {
        shape.style = style.copy()
    }

    protected fun addShape(shape: Shape) {
        shapes.add(shape)
    }

    protected fun removeShape(shape: Shape) {
        shapes.remove(shape)
    }

    abstract fun onFirstTouch(firstX: Float, firstY: Float)

    abstract fun onMove(newX: Float, newY: Float)

    abstract fun onLastTouch(lastX: Float, lastY: Float)
}
