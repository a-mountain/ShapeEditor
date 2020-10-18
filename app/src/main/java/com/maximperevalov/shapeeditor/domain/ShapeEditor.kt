package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.drawing.EllipseDrawingHandler
import com.maximperevalov.shapeeditor.domain.drawing.LineDrawingHandler
import com.maximperevalov.shapeeditor.domain.drawing.PointDrawingHandler
import com.maximperevalov.shapeeditor.domain.drawing.RectangleDrawingHandler
import com.maximperevalov.shapeeditor.domain.shapes.Rectangle
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

private val DEFAULT_SELECTED_SHAPE = SelectedShape.ELLIPSE

/**
 * Головний клас, який репрезентує редактор фігур.
 */
class ShapeEditor(width: Int, height: Int, private val drawer: Drawer) : ShapeDrawingHandler() {

    var selectedShape: SelectedShape = DEFAULT_SELECTED_SHAPE
        set(value) {
            field = value
            shapeDrawingHandler = shapeDrawingHandlerFactory.getShapeDrawingHandler(value)
        }

    private val shapes = ArrayList<Shape>()

    private val shapeDrawingHandlerFactory = ShapeDrawingHandlerFactory(shapes, currentShapeStyle)

    private var shapeDrawingHandler: ShapeDrawingHandler =
        shapeDrawingHandlerFactory.getShapeDrawingHandler(selectedShape)

    private val background = createBackground(width, height)

    fun clearLastShape() {
        if (shapes.isNotEmpty()) {
            shapes.removeLast()
        }
    }

    fun clearAllShapes() {
        shapes.clear()
    }

    fun draw() {
        drawBackground()
        drawShapes()
    }

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        shapeDrawingHandler.onFirstTouch(firstX, firstY)
    }

    override fun onMove(newX: Float, newY: Float) {
        shapeDrawingHandler.onMove(newX, newY)
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        shapeDrawingHandler.onLastTouch(lastX, lastY)
    }

    private fun drawBackground() {
        background.draw(drawer)
    }

    private fun drawShapes() {
        shapes.forEach { it.draw(drawer) }
    }

    private fun createBackground(width: Int, height: Int) =
        Rectangle(0F, 0F, width.toFloat(), height.toFloat(), Style(Color.WHITE, null))
}

private class ShapeDrawingHandlerFactory(shapes: ArrayList<Shape>, style: Style) {

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
