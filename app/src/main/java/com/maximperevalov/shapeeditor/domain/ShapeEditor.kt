package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.drawing.*
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
            updateShapeDrawingHandler()
        }

    override var currentShapeStyle: Style
        get() = super.currentShapeStyle
        set(value) {
            super.currentShapeStyle = value
            updateShapeDrawingHandler()
        }

    private val shapes = ArrayList<Shape>()

    private val shapeDrawingHandlerFactory = ShapeDrawingHandlerFactory(shapes, currentShapeStyle)

    private var shapeDrawingHandler: ShapeDrawingHandler = getShapeDrawingHandler()

    private val background = createBackground(width, height)

    private fun updateShapeDrawingHandler() {
        shapeDrawingHandler = getShapeDrawingHandler()
    }

    private fun getShapeDrawingHandler() =
        shapeDrawingHandlerFactory.getShapeDrawingHandler(selectedShape, currentShapeStyle)

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
        Rectangle(
            0F,
            0F,
            width.toFloat(),
            height.toFloat(),
            Style.createStrokelessStyle(Color.WHITE)
        )
}

private class ShapeDrawingHandlerFactory(shapes: ArrayList<Shape>, style: Style) {

    private val rectDrawingHandler = RectangleDrawingHandler(shapes, style)
    private val ellipseDrawingHandler = EllipseDrawingHandler(shapes, style)
    private val lineDrawingHandler = LineDrawingHandler(shapes, style)
    private val pointDrawingHandler = PointDrawingHandler(shapes, style)
    private val circleDrawerHandler = CircleDrawerHandler(shapes, style)
    private val lineWithDoubleCircleDrawerHandler = LineWithDoubleCircleDrawerHandler(shapes, style)
    private val cubeDrawerHandler = CubeDrawerHandler(shapes, style)

    fun getShapeDrawingHandler(selectedShape: SelectedShape, style: Style) = when (selectedShape) {
        SelectedShape.RECTANGLE -> rectDrawingHandler
        SelectedShape.ELLIPSE -> ellipseDrawingHandler
        SelectedShape.LINE -> lineDrawingHandler
        SelectedShape.POINT -> pointDrawingHandler
        SelectedShape.CIRCLE -> circleDrawerHandler
        SelectedShape.LINE_WITH_DOUBLE_CIRCLE -> lineWithDoubleCircleDrawerHandler
        SelectedShape.CUBE -> cubeDrawerHandler
    }.apply { currentShapeStyle = style }
}
