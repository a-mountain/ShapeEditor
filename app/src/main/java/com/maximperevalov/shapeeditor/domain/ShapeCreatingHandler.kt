package com.maximperevalov.shapeeditor.domain

val DEFAULT_SELECTION_COLOR = Color.BLACK

/**
 *  ShapeCreatingHandler - керує процесом малювання фігури
 */

interface ShapeCreatingHandler {

    fun onFirstTouch(firstX: Float, firstY: Float)

    fun onMove(newX: Float, newY: Float)

    fun onLastTouch(lastX: Float, lastY: Float)

}
