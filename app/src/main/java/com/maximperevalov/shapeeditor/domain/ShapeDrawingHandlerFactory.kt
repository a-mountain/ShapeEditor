package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.drawing.EllipseDrawingHandler
import com.maximperevalov.shapeeditor.domain.drawing.LineDrawingHandler
import com.maximperevalov.shapeeditor.domain.drawing.PointDrawingHandler
import com.maximperevalov.shapeeditor.domain.drawing.RectangleDrawingHandler
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

class ShapeDrawingHandlerFactory(shapes: ArrayList<Shape>, style: Style) {

    private val rectDrawingHandler = RectangleDrawingHandler(shapes, style)
    private val ellipseDrawingHandler = EllipseDrawingHandler(shapes, style)
    private val lineDrawingHandler = LineDrawingHandler(shapes, style)
    private val pointDrawingHandler = PointDrawingHandler(shapes, style)

    fun getShapeDrawingHandler(selectedShape: SelectedShape): ShapeDrawingHandler {
        return when (selectedShape) {
            SelectedShape.RECTANGLE -> rectDrawingHandler
            SelectedShape.ELLIPSE -> ellipseDrawingHandler
            SelectedShape.LINE -> lineDrawingHandler
            SelectedShape.POINT -> pointDrawingHandler
        }
    }
}
