package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.drawing.*
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

class ShapeDrawingHandlerFactory(private val shapes: ArrayList<Shape>) {

    fun getShapeDrawingHandler(selectedShape: SelectedShape, style: Style) = when (selectedShape) {
        SelectedShape.RECTANGLE -> RectangleDrawingHandler(style, shapes)
        SelectedShape.ELLIPSE -> EllipseDrawingHandler(style, shapes)
        SelectedShape.LINE -> LineDrawingHandler(style, shapes)
        SelectedShape.POINT -> PointDrawingHandler(style, shapes)
        SelectedShape.CIRCLE -> CircleDrawerHandler(style, shapes)
        SelectedShape.LINE_WITH_DOUBLE_CIRCLE -> LineWithDoubleCircleDrawerHandler(style, shapes)
        SelectedShape.CUBE -> CubeDrawerHandler(style, shapes)
    }
}
