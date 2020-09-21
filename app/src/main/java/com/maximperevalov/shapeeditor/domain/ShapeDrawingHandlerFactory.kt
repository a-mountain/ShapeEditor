package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.drawing.EllipseDrawingHandler
import com.maximperevalov.shapeeditor.domain.drawing.LineDrawingHandler
import com.maximperevalov.shapeeditor.domain.drawing.PointDrawingHandler
import com.maximperevalov.shapeeditor.domain.drawing.RectangleDrawingHandler

class ShapeDrawingHandlerFactory(shapes: ArrayList<Shape>) {

    private val rectDrawingHandler = RectangleDrawingHandler(shapes)
    private val ellipseDrawingHandler = EllipseDrawingHandler(shapes)
    private val lineDrawingHandler = LineDrawingHandler(shapes)
    private val pointDrawingHandler = PointDrawingHandler(shapes)

    fun getShapeDrawingHandler(selectedShape: SelectedShape): ShapeDrawingHandler {
        return when (selectedShape) {
            SelectedShape.RECTANGLE -> rectDrawingHandler
            SelectedShape.ELLIPSE -> ellipseDrawingHandler
            SelectedShape.LINE -> lineDrawingHandler
            SelectedShape.POINT -> pointDrawingHandler
        }
    }
}
