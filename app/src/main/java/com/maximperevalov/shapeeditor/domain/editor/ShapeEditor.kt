package com.maximperevalov.shapeeditor.domain.editor

import com.maximperevalov.shapeeditor.domain.*
import com.maximperevalov.shapeeditor.domain.events.EditorEvent
import com.maximperevalov.shapeeditor.domain.events.EditorEventHandler
import com.maximperevalov.shapeeditor.domain.shapes.Rectangle
import com.maximperevalov.shapeeditor.domain.shapes.styles.DEFAULT_STYLE
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

private val DEFAULT_SELECTED_SHAPE = SelectedShape.ELLIPSE

/**
 * Головний клас, який репрезентує редактор фігур.
 */
class ShapeEditor {

    private var width: Int = 0
    private var height: Int = 0
    private lateinit var drawer: Drawer
    private lateinit var storage: Storage

    var selectedShape: SelectedShape = DEFAULT_SELECTED_SHAPE

    var currentShapeStyle: Style = DEFAULT_STYLE

    private val eventHandlers = EditorEventHandlers()

    val eventEmitter = EditorEventEmitter(eventHandlers)

    private val shapes = ArrayList<Shape>()
    private val selector = Selector()

    private var drawingHandler = HandlersManager(shapes, selectedShape, currentShapeStyle)

    private lateinit var background: Shape

    companion object {
        private var instance: ShapeEditor? = null
        fun getInstance(): ShapeEditor {
            if (instance == null) {
                instance = ShapeEditor()
            }
            return instance!!
        }
    }

    fun saveShapes() {
        storage.saveShapes(shapes)
    }

    fun setShapesFromStorage() {
        clearAllShapes()
        storage.getAllSavedShapes().forEach {
            shapes.add(it)
            eventEmitter.emitDrawNewShapeEvent(it)
        }
    }

    fun selectShape(shape: Shape) {
        selector.select(shape)
    }

    fun removeSelection() {
        selector.removeSelection()
    }

    fun init(width: Int, height: Int, drawer: Drawer, storage: Storage) {
        this.width = width
        this.height = height
        this.drawer = drawer
        this.storage = storage
        background = createBackground(width, height)
    }

    fun getShapeByHashCode(hashCode: Int) = shapes.find { it.hashCode() == hashCode }

    fun addEventHandler(event: EditorEvent, eventHandler: EditorEventHandler) {
        eventHandlers.addEventHandler(event, eventHandler)
    }

    fun clearShape(shape: Shape) {
        shapes.remove(shape)
    }

    fun clearLastShape() {
        if (shapes.isNotEmpty()) {
            val shape = shapes.removeLast()
            eventEmitter.emitRemoveShapeEvent(shape)
        }
    }

    fun clearAllShapes() {
        shapes.clear()
        eventEmitter.emitClearShapesEvent()
    }

    fun draw() {
        drawBackground()
        drawShapes()
    }

    fun onFirstTouch(firstX: Float, firstY: Float) {
        drawingHandler.updateShapeDrawingHandler(selectedShape, currentShapeStyle)
        drawingHandler.shapeDrawingHandler.onFirstTouch(firstX, firstY)
    }

    fun onMove(newX: Float, newY: Float) {
        drawingHandler.shapeDrawingHandler.onMove(newX, newY)
    }

    fun onLastTouch(lastX: Float, lastY: Float) {
        drawingHandler.shapeDrawingHandler.onLastTouch(lastX, lastY)
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
