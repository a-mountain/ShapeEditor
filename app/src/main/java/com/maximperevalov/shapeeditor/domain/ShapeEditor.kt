package com.maximperevalov.shapeeditor.domain

private val DEFAULT_SELECTED_SHAPE = SelectedShape.ELLIPSE

/**
 * Головний клас, який репрезентує редактор фігур.
 */
class ShapeEditor(width: Int, height: Int, private val drawer: Drawer): ShapeCreatingHandler {

    private val shapes = ArrayList<Shape>()

    private val shapeCreatingHandlerFactory = ShapeCreatingHandlerFactory(shapes)
    private var shapeCreatingHandler: ShapeCreatingHandler =
        shapeCreatingHandlerFactory.getShapeCreatingHandler(DEFAULT_SELECTED_SHAPE)

    val background = Background(width, height)

    fun clearLastShape() {
        if (shapes.isNotEmpty()) {
            shapes.removeLast()
        }
    }

    fun clearAllShapes() {
        shapes.clear()
    }

    fun selectShape(selectedShape: SelectedShape) {
        shapeCreatingHandler = shapeCreatingHandlerFactory.getShapeCreatingHandler(selectedShape)
    }

    fun draw() {
        drawBackground()
        drawShapes()
    }

    override fun onFirstTouch(firstX: Float, firstY: Float) {
        shapeCreatingHandler.onFirstTouch(firstX, firstY)
    }

    override fun onMove(newX: Float, newY: Float) {
        shapeCreatingHandler.onMove(newX, newY)
    }

    override fun onLastTouch(lastX: Float, lastY: Float) {
        shapeCreatingHandler.onLastTouch(lastX, lastY)
    }

    private fun drawBackground() {
        background.draw(drawer)
    }

    private fun drawShapes() {
        shapes.forEach { it.draw(drawer) }
    }
}
