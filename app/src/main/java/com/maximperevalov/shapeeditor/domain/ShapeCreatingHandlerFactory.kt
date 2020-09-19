package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.handlers.EllipseShapeCreatingHandler
import com.maximperevalov.shapeeditor.domain.handlers.LineShapeCreatingHandler
import com.maximperevalov.shapeeditor.domain.handlers.PointShapeCreatingHandler
import com.maximperevalov.shapeeditor.domain.handlers.RectangleShapeCreatingHandler

class ShapeCreatingHandlerFactory(shapes: ArrayList<Shape>) {

    private val rectCreator = RectangleShapeCreatingHandler(shapes)
    private val ellipseCreator = EllipseShapeCreatingHandler(shapes)
    private val lineCreator = LineShapeCreatingHandler(shapes)
    private val pointCreator = PointShapeCreatingHandler(shapes)

    fun getShapeCreatingHandler(selectedShape: SelectedShape): ShapeCreatingHandler {
        return when (selectedShape) {
            SelectedShape.RECTANGLE -> rectCreator
            SelectedShape.ELLIPSE -> ellipseCreator
            SelectedShape.LINE -> lineCreator
            SelectedShape.POINT -> pointCreator
        }
    }
}
