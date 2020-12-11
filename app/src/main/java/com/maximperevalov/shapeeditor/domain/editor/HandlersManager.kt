package com.maximperevalov.shapeeditor.domain.editor

import com.maximperevalov.shapeeditor.domain.SelectedShape
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandler
import com.maximperevalov.shapeeditor.domain.ShapeDrawingHandlerFactory
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

class HandlersManager(shapes: ArrayList<Shape>, selectedShape: SelectedShape, style: Style) {

    private val shapeDrawingHandlerFactory = ShapeDrawingHandlerFactory(shapes)

    var shapeDrawingHandler: ShapeDrawingHandler = getShapeDrawingHandler(selectedShape, style)
        private set

    fun updateShapeDrawingHandler(selectedShape: SelectedShape, style: Style) {
        shapeDrawingHandler =
            shapeDrawingHandlerFactory.getShapeDrawingHandler(selectedShape, style)
    }

    private fun getShapeDrawingHandler(selectedShape: SelectedShape, style: Style) =
        shapeDrawingHandlerFactory.getShapeDrawingHandler(selectedShape, style)
}